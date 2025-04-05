package nu.nooris.noorisbackend.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MenuCategory {

  STARTERS("Förrätter"),
  MEAT("Kött"),
  PASTA("Pasta"),
  SMASH_BURGER("Smash burger"),
  FISH("Fisk"),
  KEBAB("Kebab"),
  SALAD("Sallad"),
  PIZZA("Pizza"),
  BARNMENY("Barnmeny"),
  DESSERT("Desserter"),
  ALCOHOL("Alkohol");

  private final String displayName;
}
