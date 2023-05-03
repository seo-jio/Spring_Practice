package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor // final이 붙은 멤버로 생성자를 자동으로 만들어 준다.
public class BasicItemController {

    private final ItemRepository itemRepository;

//    @Autowired
//    public BasicItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

//    @PostMapping("/add")
//    public String addItemV1(@RequestParam String itemName,
//                       @RequestParam int price,
//                       @RequestParam Integer quantity, // int도 가능
//                       Model model) {
//        Item item = new Item();
//        item.setItemName(itemName);
//        item.setPrice(price);
//        item.setQuantity(quantity);
//
//        itemRepository.save(item);
//
//        model.addAttribute("item", item);
//
//        return "basic/item";
//    }

    // ModelAttribute는 요청 파라미터를 자동으로 매핑해주는 역할(by 프로퍼티 접근법)과
    // 동시에 모델에 데이터를 담아 view로 넘겨주는 기능까지 수행한다.
//    @PostMapping("/add")
//    public String addItemV2(@ModelAttribute("item") Item item, Model model) {
//
//        itemRepository.save(item);
//
//        // ModelAttribute의 name 속성과 동일한 이름으로 모델에 자동으로 값이 들어가게 된다.
////        model.addAttribute("item", item);
//
//        return "basic/item";
//    }

    // ModelAttribute의 name 속성을 지정해주지 않으면 클래스의 첫 글자를 소문자로 바꾸어 이를 name르 설정한다.
//    @PostMapping("/add")
//    public String addItemV3(@ModelAttribute Item item, Model model) {
//
//        itemRepository.save(item);
//        // ModelAttribute의 name 속성과 동일한 이름으로 모델에 자동으로 값이 들어가게 된다.
////        model.addAttribute("item", item);
//
//        return "basic/item";
//    }

    // ModelAttribute 조차 생략 가능하다.(기능은 모두 유지)
//    @PostMapping("/add")
//    public String addItemV4(Item item, Model model) {
//
//        itemRepository.save(item);
//
//        return "basic/item";
//    }


    // 웹 브라우저의 새로 고침은 마지막에 서버에 전송한 데이터를 다시 전송하게 된다.
    // 따라서 상품 등록 후 상품 상세 페이지로 이동하더라도 forward하게 되면 url이 그대로여서 새로고침 시 상품 등록을 다시 하게 되버린다.
    // 이를 해결하기 위해  Post Redirect Get(PRG)를 사용한다.
    // PRG 사용 시 post 요청 후 redirect를 통해 상품 상세 페이지로 이동한다.
//    @PostMapping("/add")
//    public String addItemV5(Item item, Model model) {
//
//        itemRepository.save(item);
//
//        return "redirect:/basic/items/" + item.getId();
//    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        // redirect할 url에 있는 변수면 redirectAttribute에서 꺼내오고 없다면 쿼리 파라미터로 추가
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}"; // 스프링에서 redirect하는 방식
    }

    /**
     * 테스트 데이터 추가
     */
    @PostConstruct
    public void init() {

        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
