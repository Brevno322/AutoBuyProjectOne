package buy.autobuy.autobuyproject.service.impl;

import buy.autobuy.autobuyproject.entity.Automobile;
import buy.autobuy.autobuyproject.repository.PromoRepository;
import buy.autobuy.autobuyproject.service.PromoService;
import buy.autobuy.autobuyproject.service.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static buy.autobuy.autobuyproject.constant.RegistrationConstant.*;
@Transactional
@Service
public class PromoServiceImpl implements PromoService {
    private PromoRepository promoRepository;



    @Autowired
    public PromoServiceImpl(PromoRepository promoRepository){

        this.promoRepository=promoRepository;
    }
    public String addPromo(int id, Automobile automobile){

    promoRepository.addPromo(id, automobile.getType(), automobile.getKpp(), automobile.getColour(),
            automobile.getMarka(), automobile.getEngine());
        return OK;
    }



}
