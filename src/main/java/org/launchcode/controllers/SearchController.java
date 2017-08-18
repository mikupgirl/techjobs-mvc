package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    @RequestMapping(value = "results", method = RequestMethod.GET) //used "get" here since we're not looking for private information
    public String search(@RequestParam String searchType, @RequestParam String searchTerm, Model model) {

        ArrayList<HashMap<String, String>> jobs;

        model.addAttribute("columns", ListController.columnChoices);

        if (searchTerm.equals("")) {
            jobs = JobData.findAll(); //finding info from jobdata
            model.addAttribute("jobs", jobs); // adds found info to hashmap above
        }
            else if (searchType.equals("all")) {
                jobs = JobData.findByValue(searchTerm);
                model.addAttribute("jobs", jobs);
            }
            else {
                jobs = JobData.findByColumnAndValue(searchType, searchTerm); //finding info from jobdata
                model.addAttribute("jobs", jobs); // adds found info to hashmap above
        }

        return "search";
    }
}
