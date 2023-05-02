package Item.demo.controller;

import Item.demo.domain.ItemDto;
import Item.demo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
  private final ItemService itemService;

  @GetMapping("/addItem")
  public String addItem(){
    return "items/addItem";
  }

  @PostMapping("/addItem")
  public String addItemProcess(@ModelAttribute ItemDto itemDto,
                               RedirectAttributes redirectAttributes){
//      @RequestParam String itemName,
//      @RequestParam Integer price,
//      @RequestParam Integer quantity){
    itemService.saveItem(itemDto);
    redirectAttributes.addAttribute("message1", true);
    return "redirect:/items/itemList";
  }

  @GetMapping("/itemList")
  public String itemList(Model model) {
    model.addAttribute("items", itemService.getItems());
    return "items/itemList";
  }

  @GetMapping("/itemInfo/{id}")
  public String itemInfo(@PathVariable Long id,
                         Model model) {
    model.addAttribute("item", itemService.getItem(id));
    return "items/itemInfo";
  }

  @GetMapping("/updateItem/{id}")
  public String itemUpdate(@PathVariable Long id,
                           Model model) {
    model.addAttribute("item", itemService.getItem(id));
    model.addAttribute("message","수정화면입니다.");
    model.addAttribute("message1","수정하려는 필드의");
    model.addAttribute("message2","값을 수정하세요.");
    return "items/updateItem";
  }

  @PostMapping("/updateItem/{id}")
  public String itemUpdateProcess(@PathVariable Long id,
                                  @ModelAttribute ItemDto itemDto,
                                  RedirectAttributes redirectAttributes){
    itemService.updateItem(id, itemDto);
    redirectAttributes.addAttribute("message2",true);
    return "redirect:/items/itemList";
  }
}

