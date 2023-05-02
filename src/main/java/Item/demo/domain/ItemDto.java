package Item.demo.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemDto {
  private String itemName;
  private Integer price;
  private Integer quantity;

  public ItemDto(){}

  public ItemDto(String itemName, Integer price, Integer quantity){
    this.itemName = itemName;
    this.price = price;
    this.quantity = quantity;
  }
}
