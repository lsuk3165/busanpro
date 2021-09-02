package com.com.busantourisme.model.festival;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Festival {
   private int festivalId;
   private String festivalTitle;
   private String festivalAddr;
   private String usageDay;
   private String festivalArea;
   private String homepage;
   private String thumb;
   private String traffic;
   private String lat;
   private String lng;
}
