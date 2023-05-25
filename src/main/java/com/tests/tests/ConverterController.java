package com.tests.tests;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.tests.tests.Converter.*;

@Controller
public class ConverterController {

    @GetMapping("/")
    public String getConverter(Model model) {
        model.addAttribute("history", getConverterHistoryString());
        System.out.println(getConverterHistoryString());
        return "converter";
    }

    @PostMapping("/convert/octal-to-hexadecimal")
    public String converterOctToHex(RedirectAttributes redirectAttrs, Model model, @RequestParam("octalNumber") String octalNumber) {
        redirectAttrs.addFlashAttribute("octalNumber", octalNumber);
        String hexadecimalNumber;
        try {
            hexadecimalNumber = octalToHexadecimalConverter(octalNumber);
            redirectAttrs.addFlashAttribute("hexadecimalNumber", hexadecimalNumber);
        } catch (NumberFormatException e) {
            redirectAttrs.addFlashAttribute("exceptionMessage", e.getMessage());
        }
        return "redirect:/";
    }

    @PostMapping("/convert/hexadecimal-to-octal")
    public String converterHexToOct(RedirectAttributes redirectAttrs, @RequestParam("hexadecimalNumber") String hexadecimalNumber) {
        redirectAttrs.addFlashAttribute("hexadecimalNumber", hexadecimalNumber);
        String octalNumber;
        try {
            octalNumber = hexadecimalToOctalConverter(hexadecimalNumber);
            redirectAttrs.addFlashAttribute("octalNumber", octalNumber);
        } catch (NumberFormatException e) {
            redirectAttrs.addFlashAttribute("exceptionMessage", e.getMessage());
        }
        return "redirect:/";
    }
}
