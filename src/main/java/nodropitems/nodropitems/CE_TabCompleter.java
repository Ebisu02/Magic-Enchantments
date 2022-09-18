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
        List<String> list_ench_name = Arrays.asList("vampiric", "poison", "nodrop", "driven_by_the_wind");
        List<String> list_lvl_value = Arrays.asList("1", "2");

        String input = args[0].toLowerCase();

        List<String> completions = null;
        for (String s: list_ench_name) {
            if (s.startsWith(input)) {
                if (completions == null) {
                    completions = new ArrayList();
                }
                completions.add(s);
            }
        }

        if (completions != null) {
            Collections.sort(completions);
        }

        return completions;
    }
}
