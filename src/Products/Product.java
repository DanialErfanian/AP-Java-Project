package Products;

import Logic.Constants;

public enum Product {
    WOOL, MILK, EGG;

    public int getSellProfit(){
        if(this == Product.EGG)
            return Constants.EGG_SELL_PROFIT;
        else if (this == Product.MILK)
            return Constants.MILK_SELL_PROFIT;
        else
            return Constants.WOOL_SELL_PROFIT;
    }

    public int getBuyProfit() {
        if(this == Product.EGG)
            return Constants.EGG_BUY_PROFIT;
        else if (this == Product.MILK)
            return Constants.MILK_BUY_PROFIT;
        else
            return Constants.WOOL_BUY_PROFIT;
    }
}
