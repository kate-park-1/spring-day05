package Item.demo.repository;

import Item.demo.domain.Item;
import Item.demo.domain.ItemDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.thymeleaf.standard.expression.Each;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryItemRepositoryTest {

  MemoryItemRepository repository = new MemoryItemRepository();
  @AfterEach
  void setUp() {
    repository.clear();
  }

  @Test
  void 아이템등록테스트() {
    // given
    ItemDto itemDto = new ItemDto("itemA", 1000, 10);
    // when
    Item saveItem = repository.save(itemDto);
    Item findItem = repository.findById(1L);
    // then
    assertThat(saveItem).isSameAs(findItem);
  }

  @Test
  void 아이템수정테스트() {
    // given
    ItemDto itemDto = new ItemDto("itemA", 1000, 10);
    Item saveItem = repository.save(itemDto);
    ItemDto updateDto = new ItemDto("itemA", 2000, 20);
    // when
    repository.update(saveItem.getId(), updateDto);
    // then
    assertThat(saveItem.getItemName()).isEqualTo("itemA");
    assertThat(saveItem.getPrice()).isEqualTo(2000);
    assertThat(saveItem.getQuantity()).isEqualTo(20);
  }

  @Test
  void 전체아이템조회(){
    // given
    ItemDto itemDto1 = new ItemDto("itemA", 1000, 10);
    Item item1 = repository.save(itemDto1);
    ItemDto itemDto2 = new ItemDto("itemB", 1000, 10);
    Item item2 = repository.save(itemDto2);
    // when
    List<Item> itemList = repository.findAll();
    // then
    assertThat(itemList.size()).isEqualTo(2);
    assertThat(item2.getId()).isEqualTo(2L);
  }
}