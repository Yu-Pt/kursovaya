<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">

<@c.page>

<figure class="text-center">
    <blockquote class="blockquote">
        <p>Все мероприятия</p>
    </blockquote>
</figure>
</br>
<div class="form-row">
    <div class="form-group col-md-6">
        <form method="get" action="/events" class="form-inline">
            <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Введите название...">
            <button type="submit" class="btn btn-warning ml-2">Поиск</button>
        </form>
    </div>
</div>

<div class="card-columns">
    <#list events as event>
        <div class="card my-3">
                <img src="/img/${event.filename}" class="card-img-top">
            <div class="m-2">
                <h5 class="card-title">${event.title}</h5>
                <h6 class="card-subtitle mb-2 text-muted">Цена: ${event.price} руб.</h6>
                <p class="card-text">${event.description}</p>
                <p class="card-text">Дата: ${event.date}</p>
                <p class="card-text">Место проведения: ${event.place}</p>
                <a href="/events/user-events/${event.author.id}" class="card-link">Автор: ${event.authorName}</a>
                <a href="#" class="btn btn-warning">В корзину</a>
            </div>
            <div class="card-footer text-muted">
                <#if event.author.id == currentUserId>
                    <a class="btn btn-warning" href="/events/user-events/${event.author.id}?event=${event.id}">
                        Редактировать
                    </a>
                </#if>
            </div>
        </div>
    <#else>
        Нет мероприятий
    </#list>
</div>
</@c.page>