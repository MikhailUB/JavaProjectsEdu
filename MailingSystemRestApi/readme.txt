fetch('/mailing/create', { method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({ mailingType: "LETTER", startOfficeZip: "344000",
 recipientOfficeZip: "344010", recipientAddress: "Ростов-на-Дону", recipientName: "Василий Петрович" })}).then(console.log)

fetch('/mailing/send', { method: 'PUT', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({ mailingId: 5, currentOfficeZip: "344000",
 toOfficeZip: "344010" })}).then(console.log)

fetch('/mailing/accept', { method: 'PUT', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({ mailingId: 5, currentOfficeZip: "344010" })}).then(console.log)

fetch('/mailing/deliver', { method: 'PUT', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({ mailingId: 5, currentOfficeZip: "344010" })}).then(console.log)
