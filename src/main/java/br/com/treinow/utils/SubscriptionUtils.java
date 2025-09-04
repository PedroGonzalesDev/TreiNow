package br.com.treinow.utils;

import br.com.treinow.models.entities.MembershipEntity;

import java.time.LocalDate;

public class SubscriptionUtils {

    public static LocalDate calculateEndDate(LocalDate startDate, MembershipEntity plan){
        String unit = plan.getDurationType().toLowerCase();
        Long value = plan.getDuration();

        return switch (unit) {
            case "dias" -> startDate.plusDays(value);
            case "meses" -> startDate.plusMonths(value);
            case "anos" -> startDate.plusYears(value);
            default -> startDate.plusMonths(1); //Plano padrão 1 meses
        };
    }

}
