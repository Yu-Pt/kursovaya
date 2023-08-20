<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">

<@c.page>
<figure class="text-center">
    <blockquote class="blockquote">
        <p>Наши художества</p>
    </blockquote>
</figure>


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
        Ничего нет
    </#list>
</div>
</@c.page>