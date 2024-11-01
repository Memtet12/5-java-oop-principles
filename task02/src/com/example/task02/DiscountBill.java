package com.example.task02;

public class DiscountBill extends Bill{
    /**
     * @discount Скидка в десятичном виде
     */
    private final double discount;

    DiscountBill(double discount)
    {
        this.discount=discount;
    }
    /**
     * Выводит скидку в процентах
     *
     * @return  Скидка в процентах
     */
    public double getDiscount()
    {
        return discount*100;
    }

    /**
     * Подсчитывает общую сумму покупки со скидкой!!!
     *
     * @return общая стоимость покупки со скидкой!!!
     */
    @Override
    public long getPrice()
    {
        return Math.round(super.getPrice()*(1-discount));
    }

    public long calculateAbsoluteDiscount()
    {
        return super.getPrice()-getPrice();
    }

    @Override
    public String toString()
    {
        return super.toString()
                + "\nСкидка: "
                + getDiscount() + "%"
                + "%\nАбсолютное значение скидки: "
                + calculateAbsoluteDiscount()
                + "\nИтоговая сумма: " + getPrice();
    }
}
