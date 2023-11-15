package telran.net.examples;

import java.io.Serializable;
import java.time.LocalDate;

public record DateDays(LocalDate date, int days) implements Serializable {

}