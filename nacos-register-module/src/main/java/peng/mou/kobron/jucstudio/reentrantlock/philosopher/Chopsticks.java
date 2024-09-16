package peng.mou.kobron.jucstudio.reentrantlock.philosopher;

import lombok.Data;

@Data
public class Chopsticks {
    private String number;

    public Chopsticks (String number){
        this.number = number;
    }
}
