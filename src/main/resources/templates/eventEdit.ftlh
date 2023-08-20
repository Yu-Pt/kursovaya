<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">

<@c.page>
<#if event??>
<a class="text" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Редактировать мероприятие
</a>
<div class="collapse <#if event??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control" ${(titleError??)?string('is-invalid', '')}"
                       value="<#if event??>${event.title}</#if>" name="title" placeholder="Название" />
                <#if titleError??>
                    <div class="invalid-feedback">
                        ${titleError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" class="form-control"
                       value="<#if event??>${event.price}</#if>" name="price" placeholder="Цена">
                <#if priceError??>
                    <div class="invalid-feedback">
                        ${priceError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" class="form-control"
                       value="<#if event??>${event.description}</#if>" name="description" placeholder="Описание">
                 <#if descriptionError??>
                                <div class="invalid-feedback">
                          ${descriptionError}
                                </div>
                            </#if>
                        </div>
            <div class="form-group">
                <input type="text" class="form-control"
                       value="<#if event??>${event.date}</#if>" name="date" placeholder="Дата">
                 <#if dateError??>
                                <div class="invalid-feedback">
                          ${dateError}
                                </div>
                            </#if>
                        </div>
            <div class="form-group">
                <input type="text" class="form-control"
                       value="<#if event??>${event.place}</#if>" name="place" placeholder="Место проведения">
                 <#if placeError??>
                                <div class="invalid-feedback">
                          ${placeError}
                                </div>
                            </#if>
                        </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Выберете файл</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="hidden" name="id" value="<#if event??>${event.id}</#if>" />
            <div class="form-group">
                <button type="submit" class="btn btn-warning">Сохранить</button>
            </div>
        </form>
    </div>
</div>
</#if>
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