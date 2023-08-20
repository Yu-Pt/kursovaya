<#import "parts/common.ftlh" as c>

<@c.page>
<h5>Создать мероприятие</h5>
<div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control" name="title" placeholder="Название" />
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="price" placeholder="Цена">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="description" placeholder="Описание">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="date" placeholder="Дата">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="place" placeholder="Место проведения">
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Выберете изображение</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-warning">Создать мероприятие</button>
            </div>
        </form>
    </div>
</@c.page>