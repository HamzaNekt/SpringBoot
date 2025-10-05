package ma.cigma.rest.service;

import java.util.ArrayList;
import java.util.List;
import ma.cigma.rest.service.model.Product;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements IProductService {
    private static List<Product> productRepo = new ArrayList<>();
    static {
        productRepo.add(new Product(1l,"PC PORTABLE HP"));
        productRepo.add(new Product(2l,"TV LG 32p"));
        productRepo.add(new Product(3l,"TV Sony 49p"));
        productRepo.add(new Product(4l,"Camera Sony"));
    }

    @Override
    public Product getById(Long id) {

        if (productRepo == null || productRepo.isEmpty()) {
            return null;
        }
        for (Product product : productRepo) {
            if (id.equals(product.getId())) {
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> getAll() {
        return productRepo;
    }

    @Override
    public void create(Product product) {
        productRepo.add(product);
    }

    @Override
    public void delete(Long id) {
        productRepo.remove(getById(id));
    }

    @Override
    public void update(Long id, Product product) {
        productRepo.remove(getById(id));
        product.setId(id);
        productRepo.add(product);
    }
}


