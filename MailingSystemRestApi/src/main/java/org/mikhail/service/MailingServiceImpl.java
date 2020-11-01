package org.mikhail.service;

import org.mikhail.exception.*;
import org.mikhail.model.*;
import org.mikhail.modelApi.MailingHistoryApi;
import org.mikhail.modelApi.MailingStatus;
import org.mikhail.modelApi.MovementApi;
import org.mikhail.repository.*;
import org.mikhail.util.MovementValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
/**
 * Сервис реализующий всю логику по работе с отправлениями
 * создание, передвижения, выдача, получение истории
 * обрабатывает вызовы от контроллера, оперирует данными через репозитории,
 * обрабатывает их и возвращает результат контроллеру
 */
@Service
public class MailingServiceImpl implements MailingService {
    private final MailingRepository mailingRepository;
    private final MailingMovementRepository movementRepository;
    private final PostOfficeRepository officeRepository;
    private final MovementValidator movementValidator;

    @Autowired
    public MailingServiceImpl(MailingRepository mailingRepository, MailingMovementRepository movementRepository,
                              PostOfficeRepository officeRepository, MovementValidator movementValidator) {
        this.mailingRepository = mailingRepository;
        this.movementRepository = movementRepository;
        this.officeRepository = officeRepository;
        this.movementValidator = movementValidator;
    }

    @Override
    @Transactional
    public Mailing create(Mailing mailing){
        final Operation register = Operation.REGISTRATION;

        List<String> notZips = new ArrayList<>();
        PostOffice startOffice = officeRepository.findByZipCode(mailing.getStartOfficeZip());
        if (startOffice == null)
            notZips.add(mailing.getStartOfficeZip());

        PostOffice recipientOffice = startOffice;
        if (!mailing.getRecipientOfficeZip().equals(mailing.getStartOfficeZip()))
        {
            recipientOffice = officeRepository.findByZipCode(mailing.getRecipientOfficeZip());
            if (recipientOffice == null)
                notZips.add(mailing.getRecipientOfficeZip());
        }
        if (!notZips.isEmpty())
            throw new BadRequestException("Не найдены отделения с индексами " + String.join(",", notZips));

        mailing.setStartOffice(startOffice);
        mailing.setRecipientOffice(recipientOffice);
        mailing = mailingRepository.save(mailing);

        MailingMovement movement = new MailingMovement(mailing, startOffice, null, register);
        movementRepository.save(movement);

        return mailing;
    }

    private void checkExistsZipCodes(String[] zipCodes) {
        Set<String> notZips = Arrays.stream(zipCodes)
                .filter(zip -> officeRepository.findByZipCode(zip) == null)
                .collect(Collectors.toSet());
        if (!notZips.isEmpty())
            throw new BadRequestException("Не найдены отделения с индексами " + String.join(",", notZips));
    }

    @Override
    public List<Mailing> findAll() {
        return mailingRepository.findAll();
    }

    @Override
    public MailingMovement send(MovementApi movement) {
        final Operation departure = Operation.DEPARTURE;

        movementValidator.validate(movement, true);

        MailingMovement result = new MailingMovement(movementValidator.getMailing(), movementValidator.getCurrentOffice(),
                movementValidator.getNextOffice(), departure);
        result = movementRepository.save(result);

        return result;
    }

    @Override
    public MailingMovement accept(MovementApi movement) {
        final Operation arrival = Operation.ARRIVAL;

        movementValidator.validate(movement, false);

        MailingMovement result = new MailingMovement(movementValidator.getMailing(), movementValidator.getCurrentOffice(),
                movementValidator.getNextOffice(), arrival);
        result = movementRepository.save(result);

        return result;
    }

    @Override
    public MailingMovement deliver(MovementApi movement) {
        final Operation delivery = Operation.DELIVERY;

        movementValidator.validate(movement, false);

        MailingMovement result = new MailingMovement(movementValidator.getMailing(), movementValidator.getCurrentOffice(),
                movementValidator.getNextOffice(), delivery);
        result = movementRepository.save(result);

        return result;
    }

    @Override
    public MailingHistoryApi getHistory(Long mailingId) {

        List<MailingMovement> list = movementRepository.findAllByMailingId(mailingId);

        Mailing mailing = null;
        MailingStatus status = MailingStatus.NOT_FOUND;
        if (!list.isEmpty()) {
            MailingMovement lastMovement = list.get(0);
            mailing = lastMovement.getMailing();

            // вычисляем статус по офису назначения, последней операции и ее типу
            if (lastMovement.getPostOffice().equals(mailing.getRecipientOffice())) {
                if (lastMovement.getOperation() == Operation.DELIVERY) {
                    status = MailingStatus.DELIVERED;
                }
                else {
                    status = MailingStatus.READY_TO_ISSUE;
                }
            }
            else if (lastMovement.getOperation() == Operation.REGISTRATION) {
                status = MailingStatus.CREATED;
            }
            else {
                status = MailingStatus.IN_TRANSIT;
            }
        }
        MailingHistoryApi result = new MailingHistoryApi(mailing, status, list);
        return result;
    }

    @Override
    public List<PostOffice> initialize() {
        List<PostOffice> offices = officeRepository.findAll();
        if (offices.isEmpty()) {
            PostOffice office = new PostOffice("344000", "Центральное отделение", "Ростов-на-Дону");
            officeRepository.save(office);

            office = new PostOffice("344010", "10-е отделение", "Ворошиловский пр-кт, Ростов-на-Дону");
            officeRepository.save(office);

            office = new PostOffice("344020", "20-е отделение", "Буденовский пр-кт, Ростов-на-Дону");
            officeRepository.save(office);

            office = new PostOffice("344030", "30-е отделение", "Космонавтов пр-кт, Ростов-на-Дону");
            officeRepository.save(office);

            office = new PostOffice("344040", "40-е отделение", "Стачки пр-кт, Ростов-на-Дону");
            officeRepository.save(office);

            offices = officeRepository.findAll();
        }
        return offices;
    }
}
