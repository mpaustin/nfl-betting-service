package com.ex.web;

import com.ex.app.dto.BetDTO;
import com.ex.app.entities.Bet;
import com.ex.app.entities.User;
import com.ex.app.service.BetService;
import com.ex.app.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/bets")
public class BetController {
    private final Logger LOG = Logger.getLogger(this.getClass());

    private BetService betService;
    private UserService userService;

    @Autowired
    public void setBetService(BetService betService) {
        this.betService = betService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @GetMapping(path = "/getbets", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Bet> retrieveBets(@RequestParam String action, @RequestParam String username){
        List<Bet> bets = null;

        // switch to get user bets (all, completed, or not completed)
        try {
            switch (action) {

                case "all":
                    LOG.debug("BetController: Attempting to get all user bets");
                    User user = (User)userService.getById(username);
                    bets = betService.getUserAllBets(user);
                    break;
                case "completed":
                    LOG.debug("BetController: Attempting to get all user completed bets");
                    bets = betService.getUserCompBets(username);
                    break;
                case "pending":
                    LOG.debug("BetController: Attempting to get all user not completed bets");
                    bets = betService.getUserNotCompBets(username);
                    break;
            }
            if(bets == null) {
                throw new IOException();
            }
            } catch(IOException ex){
                LOG.info("BetController: No bets to find");
                return null;
            } catch(Exception ex){
                LOG.error("BetController: Error with BetServlet: retrieving bets");
                ex.printStackTrace();
                return null;
            }

        return bets;
    }

    @CrossOrigin
    @PostMapping(path = "/newbet", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean createNewBet(@RequestBody BetDTO betDTO){
        boolean enoughFunds = false;
        float fundsAvail;

        LOG.debug("BetController: Attempting to create new bet");
        try {
            User user = (User)userService.getById(betDTO.getBetUser());
            fundsAvail = user.getUserFunds();

            if(betDTO.getBetAmount() > fundsAvail) {
                return enoughFunds;
            }

            user.setUserFunds(fundsAvail - betDTO.getBetAmount());
            userService.update(user);

            Bet bet = new Bet();

            bet.setBetUser(user);
            bet.setBetAmount(betDTO.getBetAmount());
            bet.setBetTeam(betDTO.getBetTeam());
            bet.setGameId(betDTO.getGameId());
            bet.setFavTeam(betDTO.getFavTeam());
            bet.setDogTeam(betDTO.getDogTeam());
            bet.setLine(betDTO.getLine());
            bet.setGameDateTime(betDTO.getGameDateTime());
            bet.setCompleted(betDTO.isCompleted());

            betService.addNew(bet);
            LOG.info("BetController: Successfully added new bet");
            enoughFunds = true;
            return enoughFunds;
        } catch(Exception ex) {
            LOG.error("BetController: Error with BetServlet: add new bet");
            ex.printStackTrace();
            return false;
        }
    }
}
