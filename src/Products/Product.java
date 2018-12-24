package Products;

import Logic.Constants;

public enum Product {
    WOOL, MILK, EGG, BEAR, LION;

    public int getSellProfit(){
        if(this == Product.EGG)
            return Constants.EGG_SELL_PROFIT;
        else if (this == Product.MILK)
            return Constants.MILK_SELL_PROFIT;
        else if (this == Product.WOOL)
            return Constants.WOOL_SELL_PROFIT;
        else if (this == Product.BEAR)
            return Constants.BEAR_SELL_PROFIT;
        else if (this == Product.LION)
            return Constants.LION_SELL_PROFIT;
        return 0;
    }

    public int getBuyProfit() {
        if(this == Product.EGG)
            return Constants.EGG_BUY_PROFIT;
        else if (this == Product.MILK)
            return Constants.MILK_BUY_PROFIT;
        else if (this == Product.WOOL)
            return Constants.WOOL_BUY_PROFIT;
        else if (this == Product.BEAR)
            return Constants.BEAR_BUY_PROFIT;
        else if (this == Product.LION)
            return Constants.LION_BUY_PROFIT;
        return 0;
    }
}
