package org.rza.mpei.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Measurement {

    private String id;

    private float Ia;

    private float Ib;

    private float Ic;

}
