package nodropitems.nodropitems;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CE_TabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list_ench_name = Arrays.asList("vampiric", "poison", "nodrop", "driven_by_the_wind", "freezing");
        List<String> list_ench_ONELVL = Arrays.asList("nodrop", "driven_by_the_wind");
        List<String> list_ench_TWOLVL = Arrays.asList("vampiric", "poison", "freezing");
        String input = args[0].toLowerCase();

        if (args.length >= 2) {
            return null;
        }

        List<String> completions = null;
        for (String s: list_ench_name) {
            if (s.startsWith(input) && !list_ench_name.contains(input)) {
                if (completions == null) {
                    completions = new ArrayList();
                }
                completions.add(s);
            }
            else if (list_ench_ONELVL.contains(input)) {
                if (completions == null) {
                    completions = new ArrayList();
                }
                completions.add("1");
                return completions;
            }
            else if (list_ench_TWOLVL.contains(input)) {
                if (completions == null) {
                    completions = new ArrayList();
                }
                completions.add("1");
                completions.add("2");
                return completions;
            }
            else {
                return null;
            }
        }

        if (completions != null) {
            Collections.sort(completions);
        }

        return completions;
    }
}
