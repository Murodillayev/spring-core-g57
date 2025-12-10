package uz.pdp;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ProductService {

    private final ProductPotgresDao productPotgresDao;
    private final ProductMySqlDao productMySqlDao;

    public ProductService( ProductPotgresDao productPotgresDao, ProductMySqlDao productMySqlDao) {
        this.productPotgresDao = productPotgresDao;
        this.productMySqlDao = productMySqlDao;
    }


    public void m1() {
        System.out.println("m1 method works");
        productPotgresDao.save();

    }

    public void m2() {
        System.out.println("m2 method works");
        productMySqlDao.save();

    }


}


// lazy, eager

//  -> fetch type lazy (soraganda yaratiladi)
//  -> fetch type eager (ioc ishga tushishi bilan yaratiladi)

//default
// singleton -> eager
// protype -> lazy