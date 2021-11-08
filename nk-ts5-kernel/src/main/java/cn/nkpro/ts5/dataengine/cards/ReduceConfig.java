package cn.nkpro.ts5.dataengine.cards;

import cn.nkpro.ts5.basic.Keep;
import lombok.Data;

@Keep
@Data
public class ReduceConfig{

    private String docId;
    private String cardKey;

    private String preTask;
    private String programType;
    private String dataSource;
    private String dataCommand;
    private String service;
    private String dataKey;
    private String dataMapping;
}