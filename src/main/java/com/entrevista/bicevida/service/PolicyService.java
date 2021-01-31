package com.entrevista.bicevida.service;

import com.entrevista.bicevida.dto.Copago;
import com.entrevista.bicevida.dto.PolicyData;
import com.entrevista.bicevida.dto.ResultPolicy;
import com.entrevista.bicevida.model.Policy;
import com.entrevista.bicevida.model.Workers;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

@Service
public class PolicyService {

    private final Double coberturaSaludsinHijos = 0.279;
    private final Double coberturaSaludUnHijo = 0.4396;
    private final Double coberturaSaludDosOMasHijo = 0.5599;

    private final Double coberturaDentalsinHijos = 0.12;
    private final Double coberturaDentalUnHijo = 0.1950;
    private final Double coberturaDentalDosOMasHijo = 0.2480;

    Predicate<Workers> validacionNinos = (workers)-> {
        if(workers.getChilds() == 0 && workers.getAge() <= 65){
            return true;
        }else if( workers.getChilds() == 1 && workers.getAge() <= 65){
            return true;
        }else if(workers.getChilds() >= 2 && workers.getAge() <= 65){
            return true;
        }else {
            return false;
        }
    };

    BiFunction<Double, Double, Double> calculaCopagoTrabajador = (valorTotalCobertura, porcentaje) -> valorTotalCobertura *  (1 - porcentaje);



    public Mono<ResultPolicy> convertirData(Mono<PolicyData> dataApiExterna){

        Mono<ResultPolicy> respuestaFinal = dataApiExterna.map(data -> {
            ResultPolicy ret = new ResultPolicy();
            List<Copago> listaCopagoPorTrabajador = new ArrayList<>();

            Double company_percentage = (double) data.getPolicy().getCompany_percentage() / 100;

            boolean coberturaDental = data.getPolicy().getHas_dental_care();
            System.out.println("Data " + coberturaDental);

            data.getPolicy().getWorkers().forEach(workers -> workers.forEach((individualWorker) -> {
                Copago copago = new Copago();
                double sumaTotal;
                Double copagoTrabajador;
                System.out.println("edad " + individualWorker.getAge() + " hijos " + individualWorker.getChilds());
                if (coberturaDental) {
                    if (validacionNinos.test(individualWorker)) {
                        sumaTotal = ret.getValorPolizaEmpresa() + coberturaSaludsinHijos + coberturaDentalsinHijos;
                        copagoTrabajador = calculaCopagoTrabajador.apply(sumaTotal, company_percentage);
                        copago.setCopago(copagoTrabajador);
                        copago.setNroWorker(listaCopagoPorTrabajador.size()-1);
                        ret.setValorPolizaEmpresa(sumaTotal);

                    } else if (validacionNinos.test(individualWorker)) {

                        sumaTotal = ret.getValorPolizaEmpresa() + coberturaSaludUnHijo + coberturaDentalUnHijo;
                        copagoTrabajador = calculaCopagoTrabajador.apply(sumaTotal, company_percentage);
                        copago.setCopago(copagoTrabajador);
                        copago.setNroWorker(listaCopagoPorTrabajador.size()-1);
                        ret.setValorPolizaEmpresa(sumaTotal);
                    } else if (validacionNinos.test(individualWorker)) {

                        sumaTotal = ret.getValorPolizaEmpresa() + coberturaSaludDosOMasHijo + coberturaDentalDosOMasHijo;
                        copagoTrabajador = calculaCopagoTrabajador.apply(sumaTotal, company_percentage);
                        copago.setCopago(copagoTrabajador);
                        copago.setNroWorker(listaCopagoPorTrabajador.size()-1);
                        ret.setValorPolizaEmpresa(sumaTotal);
                    }
                } else {
                    if (validacionNinos.test(individualWorker)) {
                        sumaTotal = ret.getValorPolizaEmpresa() + coberturaSaludsinHijos;
                        copagoTrabajador = calculaCopagoTrabajador.apply(sumaTotal, company_percentage);
                        copago.setCopago(copagoTrabajador);
                        copago.setNroWorker(listaCopagoPorTrabajador.size()-1);
                        ret.setValorPolizaEmpresa(sumaTotal);

                    } else if (validacionNinos.test(individualWorker)) {
                        sumaTotal = ret.getValorPolizaEmpresa() + coberturaSaludUnHijo;
                        copagoTrabajador = calculaCopagoTrabajador.apply(sumaTotal, company_percentage);
                        copago.setCopago(copagoTrabajador);
                        //listaCopagoPorTrabajador.add(copago);
                        copago.setNroWorker(listaCopagoPorTrabajador.size()-1);
                        //ret.setValorPolizaEmpresa(sumaTotal);
                    } else if (validacionNinos.test(individualWorker)) {

                        sumaTotal = ret.getValorPolizaEmpresa() + coberturaSaludDosOMasHijo;
                        copagoTrabajador = calculaCopagoTrabajador.apply(sumaTotal, company_percentage);
                        copago.setCopago(copagoTrabajador);
                        //listaCopagoPorTrabajador.add(copago);
                        copago.setNroWorker(listaCopagoPorTrabajador.size()-1);
                        ret.setValorPolizaEmpresa(sumaTotal);
                    }
                }
                listaCopagoPorTrabajador.add(copago);

            }));
            //calculamos el porcentaje de la empresa
            ret.setValorPolizaEmpresa(ret.getValorPolizaEmpresa() * company_percentage);
            ret.setListaCopagoPorWorker(listaCopagoPorTrabajador);
            //esto lo devuelve a la variable
            return ret;
        });

        //este devuelve el resultado final al controller
        return respuestaFinal;
    }

    public Map<Integer, Copago> usandoFilter(Mono<PolicyData> dataApiExterna){

        return null;
    }

}
