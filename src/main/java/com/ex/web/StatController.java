package com.ex.web;

import com.ex.app.entities.Player;
import com.ex.app.service.StatService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatController {

        private final Logger LOG = Logger.getLogger(this.getClass());

        private StatService statService;

        @Autowired
        public void setStatService(StatService statService) {
            this.statService = statService;
        }


        @CrossOrigin
        @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
        public List<Player> retrieveStats(@RequestParam String action, @RequestParam String category, @RequestParam String team, @RequestParam String firstname, @RequestParam String lastname){
            List<Player> statistics = null;

            try {
                switch (action) {

                    case "leaders":
                        LOG.debug("StatController: Attempting to get all stat leaders");
                        statistics = statService.getLeaders(category);
                        break;
                    case "individuals":
                        LOG.debug("StatController: Attempting to get all stats for a player");
                        statistics = statService.getByName(firstname, lastname);
                        break;
                    case "teams":
                        LOG.debug("StatController: Attempting to get all stats for a team");
                        statistics = statService.getByTeam(team);
                        break;
                }
                if(statistics == null) {
                    throw new IOException();
                }
            } catch(IOException ex){
                LOG.info("StatController: No stats to find");
                return null;
            } catch(Exception ex){
                LOG.error("StatController: Error with StatServlet: retrieving stats");
                ex.printStackTrace();
                return null;
            }

            return statistics;
        }
}
