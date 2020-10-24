<#import "parts/common.ftlh" as common>
<#import "parts/login.ftlh" as login>

<@common.page>
Registration new user
${message?ifExists}
<@login.login "/registration" />
<a href="/login">Login if already registered</a>
</@common.page>