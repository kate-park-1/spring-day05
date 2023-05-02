package Item.demo.repository;

import Item.demo.domain.Item;
import Item.demo.domain.ItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class MemoryItemRepository implements ItemRepository {
  private static final Map<Long, Item> store = new HashMap<>();
  private static Long sequence = 0L;

  // 전체 상품 조회 , 상품등록(이름,가격,수량) , 상품조회(id), 상품 수정(id, 수정하고자하는 필드들)

  public List<Item> findAll(){ // 전체 상품 조회
    return new ArrayList<>(store.values());
  }

  public Item save(ItemDto itemDto){
    Item item = new Item(itemDto.getItemName(), itemDto.getPrice(), itemDto.getQuantity());
    item.setId(++sequence);
    store.put(item.getId(), item);
    return item;
  }

  @Override
  public Item findById(Long id) {
    return store.get(id);
  }

  @Override
  public void update(Long id, ItemDto itemDto) {
    Item item = findById(id);
    item.setItemName(itemDto.getItemName());
    item.setQuantity(itemDto.getQuantity());
    item.setPrice(itemDto.getPrice());
    //store.put(item.getId(),item); //==> 결과 확인 필요
  }

  public void clear() {
    store.clear();
    sequence = 0L;
  }
}
