package ma.web.productbackend.exeption;

public class ProductNotFoundExeption extends RuntimeException{

    public ProductNotFoundExeption(String msg){
        super(msg);
    }
}
