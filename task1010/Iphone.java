package com.javarush.task.pro.task10.task1010;
/*
Переопределяем метод equals у объекта и сравниваем два айфона.

note: необходимость написать свой equals возникает так как дефолтный equals у Object просто сравнивает ссылки - то есть он МАКСИМУМ может лишь сказать тот ли это объект.
А на два одинаковых объекта скажет false/
*/
public class Iphone {
    private String model;
    private String color;
    private int price;

    public Iphone(String model, String color, int price) {
        this.model = model;
        this.color = color;
        this.price = price;
    }


    public boolean equals(Object obj) {

        //сссылки у текущего и переданного равны = возвращаем true. Объект совпадает сам с собой
        if (this == obj)
            return true;

        //передали ссылку null а значит сравнивать не с чем. Объект который рассматриваем точно не null . - сразу сходу возвращаем false
        if (obj == null)
            return false;
        //обычно считается что равны могут быть объекты если это объекты одного класса. Поэтому тут смотрим объект какого класса нам пришел. (наш ожидаемый класс это Iphone).
        // inscanceof оператор - говорит нам какого типа объект
        if (!(obj instanceof Iphone))
            return false;
        //пора сравнивать объект. Преобразовываем попавший к нам Object к нашему типу Iphone
        Iphone iphone = (Iphone) obj;

        /*
        дальше пойдем сравнивать уже внутренности объектов (note: стринги сравниваем НЕ просто через "==" потому что это могут быть разные два объекта а не один и тот же)
         */

        // сначало сравниваем int чтобы быстрее обработать
        if (this.price != iphone.price)
            return false;

        /*
        сравниваем на предмет null - так быстрее; нет смысла через equals каждый элемент смотреть переменной если она null;
         */

        //обрабатываем ситуацию если вдруг model где-то null
        if (this.model == null && iphone.model != null || this.model != null && iphone.model == null)
            return false;
        //обрабатываем ситуацию если вдруг color где-то null
        if (this.color == null && iphone.color != null || this.color != null && iphone.color == null)
            return false;
        //обрабатываем ситуацию если вдруг color везде null и model везде null
        if(this.color == null && iphone.color == null && this.model == null && iphone.model == null)
            return true;

        //пишем сравнения на нормальные состояния (нормально объявленные)
        boolean checkModel = this.model.equals(iphone.model);
        boolean checkColor = this.color.equals(iphone.color);

        //финалочка
        return  checkModel && checkColor;
    }

    public static void main(String[] args) {
        Iphone iphone1 = new Iphone(null, null, 999);
        Iphone iphone2 = new Iphone(null, null, 999);

        System.out.println(iphone1.equals(iphone2));
    }
}
