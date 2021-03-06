package com.felix.fonteneau.contentdrivengraphql.model.filter;

import com.felix.fonteneau.contentdrivengraphql.model.ApplicationData;
import com.felix.fonteneau.contentdrivengraphql.model.Content;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;


@Data
@NoArgsConstructor
public class RandomFilter implements Filter {
    private Double probabilityTrue = 0.5;
    private Random random = new Random();

    public RandomFilter(Double probabilityTrue) {
        this.probabilityTrue = probabilityTrue;
    }

    @Override
    public boolean filter(Content content, ApplicationData appData) {
        return random.nextDouble() < this.probabilityTrue;
    }
}
