package com.SpringPhone.Cellphone.service;

import com.SpringPhone.Cellphone.model.Celular;
import com.SpringPhone.Cellphone.model.Marca;
import com.SpringPhone.Cellphone.repository.CelularRepository;
import com.SpringPhone.Cellphone.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
public class DBservice {

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private CelularRepository celularRepository;

    public void instanciaDb(){
        Marca m1 = new Marca("Apple");
        Marca m2 = new Marca("Samsung");
        Marca m3 = new Marca("Motorola");

        Celular c1 = new Celular("Iphone-16-Pro", new BigDecimal("8499.00"), 2024, m1, 15);
        Celular c2 = new Celular("Iphone-15-Plus", new BigDecimal("5299.90"), 2023, m1, 8);
        Celular c3 = new Celular("Iphone-14", new BigDecimal("3899.00"), 2022, m1, 20);
        Celular c4 = new Celular("Iphone-13-Mini", new BigDecimal("3400.00"), 2021, m1, 5);
        Celular c5 = new Celular("Iphone-SE-2022", new BigDecimal("2999.00"), 2022, m1, 12);

        Celular c6 = new Celular( "Galaxy-S24-Ultra", new BigDecimal("7299.00"), 2024, m2, 10);
        Celular c7 = new Celular( "Galaxy-A55", new BigDecimal("1999.90"), 2024, m2, 25);
        Celular c8 = new Celular("Galaxy-Z-Fold-6", new BigDecimal("11500.00"), 2024, m2, 3);
        Celular c9 = new Celular( "Galaxy-M54", new BigDecimal("1699.00"), 2023, m2, 18);
        Celular c10 = new Celular("Galaxy-S23-FE", new BigDecimal("2499.00"), 2023, m2, 14);

        Celular c11 = new Celular("Edge-50-Pro", new BigDecimal("3149.00"), 2024, m3, 9);
        Celular c12 = new Celular("Moto-G85", new BigDecimal("1599.00"), 2024, m3, 30);
        Celular c13 = new Celular( "Razr-50-Ultra", new BigDecimal("6499.00"), 2024, m3, 4);
        Celular c14 = new Celular( "Moto-G54", new BigDecimal("1199.00"), 2023, m3, 40);
        Celular c15 = new Celular( "Edge-40-Neo", new BigDecimal("1899.00"), 2023, m3, 11);

        m1.getCelulares().addAll(Arrays.asList(c1,c2,c3,c4,c5));
        m2.getCelulares().addAll(Arrays.asList(c6,c7,c8,c9,c10));
        m3.getCelulares().addAll(Arrays.asList(c11,c12,c13,c14,c15));

        marcaRepository.saveAll(Arrays.asList(m1,m2,m3));
        celularRepository.saveAll(Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15));
    }

}
