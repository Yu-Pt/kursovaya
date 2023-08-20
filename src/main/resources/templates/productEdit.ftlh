<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">

<@c.page>
<#if product??>
<a class="text" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Редактировать запись о художестве
</a>
<div class="collapse <#if product??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control" ${(titleError??)?string('is-invalid', '')}"
                       value="<#if product??>${product.title}</#if>" name="title" placeholder="Название" />
                <#if titleError??>
                    <div class="invalid-feedback">
                        ${titleError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" class="form-control"
                       value="<#if product??>${product.description}</#if>" name="description" placeholder="Описание">
                 <#if descriptionError??>
                                <div class="invalid-feedback">
                          ${descriptionError}
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
            <input type="hidden" name="id" value="<#if product??>${product.id}</#if>" />
            <div class="form-group">
                <button type="submit" class="btn btn-warning">Сохранить</button>
            </div>
        </form>
    </div>
</div>
</#if>
<div class="card-columns">
    <#list products as product>
        <div class="card my-3">
                    <img src="/img/${product.filename}" class="card-img-top">
                               <div class="m-2">
                                   <h5 class="card-title">${product.title}</h5>
                                   <p class="card-text">${product.description}</p>
                                   <a href="/products/user-products/${product.author.id}" class="card-link">Автор: ${product.authorName}</a>
                               </div>
                               <div class="card-footer text-muted">
                                   <#if product.author.id == currentUserId>
                              <a class="btn btn-warning" href="/products/user-products/${product.author.id}?product=${product.id}">
                                    Редактировать
                              </a>
                                   </#if>
                               </div>
                </div>
    <#else>
        Нет товаров
    </#list>
</div>
</@c.page>