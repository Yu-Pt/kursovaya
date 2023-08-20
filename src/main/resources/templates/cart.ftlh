<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">

<@c.page>
<div class="card-columns">

    <#list carts as cart>
        <div class="card my-1">
            <div class="m-2">
                <span>${cart.title}</span><br/>
                ${cart.price} руб.
            </div>
        </div>
    <#else>
        Пусто
    </#list>

</div>
<a class="btn btn-warning" href="">
                            Купить
                      </a>
</@c.page>
