<#include "security.ftlh">
<#import "login.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #ffb6c1;">
 <img src="/img/gnezdo.png" width="50" height="50" class="d-inline-block align-top" alt="">
    <a class="navbar-brand mb-0 h1" href="/main">Гнездо</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/main">Главная</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/events">Мероприятия</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/products">Художества</a>
            </li>
            <#if isAdmin>
            <li class="nav-item">
                <a class="nav-link" href="/events/add">Создать мероприятие</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/products/add">Создать художество</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/events/user-events/${currentUserId}">Мои мероприятия</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/products/user-products/${currentUserId}">Мои художества</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/user">User list</a>
            </li>
            </#if>
        </ul>

        <div class="navbar-text mr-3">
        <ul class="navbar-nav mr-auto">
            <a class="nav-link" href="/cart/${currentUserId}">Корзина</a>
            <a class="nav-link" href="/user/profile">${name}</a>
        </ul>
        </div>
        <@l.logout />
    </div>
</nav>