package com.general_hello.commands.commands;

import com.jagrosh.jdautilities.command.SlashCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;

import java.time.OffsetDateTime;

public class SendEmbedMessage extends SlashCommand {
    public SendEmbedMessage() {
        this.name = "send";
        this.help = "Sends an embed of the selected type.";
        this.userPermissions = new Permission[]{Permission.MESSAGE_MANAGE};
        this.children = new SlashCommand[]{new DiscordRules(), new ServerRules(), new PackLimits(), new DisciplineSystem(), new ReactionRole()};
    }

    @Override
    protected void execute(SlashCommandEvent event) {}

    private static class DiscordRules extends SlashCommand {
        public DiscordRules() {
            this.name = "discord_rules";
            this.help = "Sends an embed of the discord rules";
            this.userPermissions = new Permission[]{Permission.MESSAGE_MANAGE};
        }

        @Override
        protected void execute(SlashCommandEvent event) {
            TextChannel channel = event.getGuild().getTextChannelById(948972960214048778L);
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setAuthor("Discord Rules", null, event.getGuild().getIconUrl());
            embedBuilder.setFooter("Last updated at ").setTimestamp(OffsetDateTime.now());
            embedBuilder.setDescription("""
                    :one: **[RULES]**
                    -----------------------------------

                    :RedArrow_Right:[A.] ``This is an [18+] PVP server.``\s

                    + Swearing and Profanity is allowed.

                    + Do <NOT> direct any swearing or profanity at others with ill intent.

                    + Joking is alright but if other members say stop, then be respectable and stop.

                    + Please mark common phobias as a spoiler and give a warning, and please understand not all phobias will be known. If you have an obscure phobia and someone posts something that causes you to react, please swap to a different channel and let a staff member know so they can remove the content.

                    :RedArrow_Left~1:[B.] ``This is an 18+ community and joining is agreeing that you are 18+ years of age. It is not the responsibility of the staff team to verify your age beyond you telling us you are over 18 year of age. Expect this community to act as a mature community.``\s

                    + The Discord TOS states that members must be 13+, if it is found a member is under 13, then they will be removed.

                    :RedArrow_Left~1:[C.]. ``Please keep in mind this is a public space.``\s

                    :RedArrow_Left~1:[D.] ``We have people from all walks of life and from different cultures/lifestyles so use common sense in public discussions as we are all here to relax, have fun, and use this time to enjoy ourselves.``\s

                    :RedArrow_Left~1:[E.] ``Do <NOT> harass anyone, it will not be tolerated. This includes both in general chats and in DMs.``

                    + <NO> Racism/Slurs, sexism, political, hate speech, religious talk, discrimination, NSFW, talk about suicide/self harm in any chat.

                    + If things get heated a Moderator will step in and Act accordingly.

                    :RedArrow_Left~1:[F.] ``Please keep things Appropriate``

                    + This server is primarily english, please use it in all channels of Discord.

                    + Do <NOT> use excessive caps in any chats.

                    + Do <NOT> spam any chats.

                    + Use channels as intended.

                    + Do <NOT> advertise anything without permission from the head staff;  @Hound Lullaby#2793.

                    + Do <NOT> claim other people's artwork as your own, you may post others' art <IF> you give proper Credit. (Links back to their profiles).

                    + Do <NOT> talk about other people's personal information without their permission. That is Doxxing which is highly prohibited!

                    + <NO> encouraging or conducting illegal activity.

                    + Do <NOT>  Talk ill about other servers or members. Venting is okay, but keep venting away from server related things. If there is an issue with the server or another member, please fill out our feedback form or message a staff member.

                    + Attempting to abuse loopholes in these rules may still result in punishment. We expect you to use common sense when playing and interacting here.\s

                    + If an issue arises, please give staff time and space to handle it. If no staff is around at the time, please DM and active staff members and let them know. If there is any additional information Staff should know, contact one privately.

                    + Please do <NOT> argue with any staff members. If it is felt that the current staff member being talked to, is not listening or cannot help it is alright to contact another staff member with the issue. Just keep in mind that the previous staff member may be contacted about the issue.

                    + If you have any questions or concerns about a staff member, please do <not> start a public dispute as it will make it harder to assist you. You can message a head Staff(@Hound Lullaby#2793) or fill out the appropriate form so it can be dealt with privately and efficiently!

                    + Do not hesitate to ask a Staff member for help! We are here to make your gameplay as enjoyable as possible.""");
            channel.sendMessageEmbeds(embedBuilder.build()).queue();
            event.reply("Successfully sent the message!").setEphemeral(true).queue();
        }
    }

    private static class ServerRules extends SlashCommand {
        public ServerRules() {
            this.name = "server_rules";
            this.help = "Sends an embed of the server rules";
            this.userPermissions = new Permission[]{Permission.MESSAGE_MANAGE};
        }

        @Override
        protected void execute(SlashCommandEvent event) {
            TextChannel channel = event.getGuild().getTextChannelById(948972173178048522L);
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setAuthor("Server Rules", null, event.getGuild().getIconUrl());
            embedBuilder.setFooter("Last updated at ").setTimestamp(OffsetDateTime.now());
            embedBuilder.setDescription("""
                    :two: **[SERVER RULES]**
                    -----------------------------------

                    <:Arrowpoint_right:954141632180146177>  [G.] ``These guidelines are separate from the chill server regulations. This server is focused on PVP GAMEPLAY.``

                    + Do not purposefully start harassing other members in global, if conversations get to heated a Moderator will step in.\s

                    <:Arrowpoint_right:954141632180146177>  [H.] ``We encourage sportsmanship on our server - to enjoy survival without the toxic banters.``\s

                    + There is a 2-minute timer on those who just log-in to prevent insta-death, and give them time to move away.

                    <:Arrowpoint_right:954141632180146177>  [I.] ``First day of the month will be a dedicated max tier GS throughout until restart for the next day.``

                    + 15th - Free *Reskins* and *Regenders* of every month.

                    :three: **[COMBAT RULES]**
                    -----------------------------------

                    :RedArrow_Left~1: [J.] ``3-calls are used to contest for gore, if the opposing dino 3-calls back they accept the challenge. If they 4-call, you must let them walk away and you take their gore.``\s

                    :RedArrow_Left~1: [K.] ``BODY TETHER RULE - NO BODY DOWN RULE! ALL BODIES WITHIN TETHER ARE CLAIMED.``

                    + Tether does not start until a body falls from either party; even then the fight can continue until one of the opponents/packs back off and leave the area. The winning party must stay near the gore and cannot chase the other party down.\s

                    :RedArrow_Left~1: [L.] ``Carnivores may not surround or hover over another Dino's gore - they must contest or leave.``\s

                    :RedArrow_Left~1: [M.] ``Herbivores can protect themselves or their pack if a Carnivore is in the area. They must 3-call first before initiating aggression.``\s

                    + Being aggressive does NOT mean you can chase down a Carnivore endlessly, just out of your immediate area.

                    <:Arrowpoint_right:954141632180146177>  [N.] ``If a Carnivore has a successful hunt of the herbivore, the herd must allow the carnivore ample time to take the gore away or leg it and leave the area if it is in their territory.``

                    <:Arrowpoint_right:954141632180146177>  [O.] ``COMBAT LOGGING - This is not against our rules, if you decide to engage and refuse to make any moves and hover, it is on you to give them that free time to log.``

                    + ie - Actively being chase or hunted by other players.

                    <:Arrowpoint_right:954141632180146177>  [P.] ``NO BAITING! You cannot 2-Call another Dinosaur and or be friendly only to attack them.``

                    <:Arrowpoint_right:954141632180146177>  [Q.] ``NO SCOUTING ON ALTS - Do not be in a herd, and on another lead your carnivore team to your herds location.``

                    + This includes logging off and switching dinos; you **CANNOT** hunt your own herd after switching until the time limit of <40 minutes> is up.
                    :four: **[TERRITORY]**
                    -----------------------------------

                    <:Arrowpoint_right:954141632180146177>  [O.] ``All water sources can be claimed as territory``.\s

                    + If it is a big water source, you may claim a portion that is by your herd/pack, others may choose to drink from the other side as long as they are not too close to the opposite group.

                    + Dinosaurs claiming territory have a right to defend it by 3-calling an opposing party, allowing the other party time to retreat or challenge for it.\s

                    <:Arrowpoint_right:954141632180146177>  [P.] ``Nesting grounds are considered territory only in that area as long as there are nest(s) down.``

                    + This includes feeding grounds; if a area is not big enough to feed other herds, the herd that was initially there first may defend and 3-call an opposing but must give them ample time to respond and leave, or accept challenge.


                    :five: **[ENTER AT YOUR OWN RISK!]**
                    -----------------------------------""");
            channel.sendMessageEmbeds(embedBuilder.build()).queue();
            channel.sendMessage("""
                    ```ini
                    [IF YOU HAVE READ THROUGH THE RULES AND ACCEPT THESE TERMS, PLEASE REACT TO THE EMOJI BELOW IN ORDER TO GAIN THE HATCHLING ROLE AND ENTER THE DANGEROUS WORLD OF THE ISLAND.. GOOD LUCK OUT THERE.]
                    ```""").queue();
            event.reply("Successfully sent the message!").setEphemeral(true).queue();
        }
    }

    private static class PackLimits extends SlashCommand {
        public PackLimits() {
            this.name = "pack_limits";
            this.help = "Sends an embed of the pack limits";
            this.userPermissions = new Permission[]{Permission.MESSAGE_MANAGE};
        }

        @Override
        protected void execute(SlashCommandEvent event) {
            TextChannel channel = event.getGuild().getTextChannelById(948976321533472788L);
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setAuthor("Pack Limits", null, event.getGuild().getIconUrl());
            embedBuilder.setFooter("Last updated at ").setTimestamp(OffsetDateTime.now());
            embedBuilder.setDescription("""               
                    :six: **[PACKING GUIDELINES]**
                    -----------------------------------
                                        
                                        
                    <:Arrowpoint_right:954141632180146177>  [A.] ``ADULT: 1.2+``
                                        
                    <:Arrowpoint_right:954141632180146177>  [B.] ``SUB-ADULT: 1.0 - 1.199``
                                        
                    <:Arrowpoint_right:954141632180146177>  [C.] ``JUVENILE: 0.0 - 0.99``
                                        
                    <:Arrowpoint_right:954141632180146177>  [D.] ``MIXED HERD: Limitless with the exception of a max of 6 adult apex; 2 of each kind, or 3 of 2 kind.``
                                        
                    <:Arrowpoint_right:954141632180146177>  [E.] ``In Pure herds, The apex is allowed 6 of each with smaller ones.``
                                        
                    <:Arrowpoint_right:954141632180146177>  [F.] Velos are considered "Scavengers" and may 'team up' with a carni group; the group may allow the velos to stay by them and help hunt, or engage them away - Max Velos per carni group is 3.
                                        
                    :meat_on_bone:  **REX**
                    -----------------------------------
                    ``2`` Adult
                    ``3`` Juvenile\s
                    *OR*
                    ``2`` Adult
                    ``1`` Sub-Adult\s
                    ``2`` Juvenile
                                        
                    **ACRO**
                    -----------------------------------
                    ``3`` Adult
                    ``4`` Juvenile\s
                    *OR*
                    ``3`` Adult
                    ``1`` Sub-Adult\s
                    ``3`` Juvenile
                                        
                    **MEG**
                    -----------------------------------
                    ``6`` Adult
                    ``8`` Juvenile\s
                    *OR*
                    ``6`` Adult
                    ``4`` Sub-Adult\s
                    ``4`` Juvenile
                                        
                    **ICHTHY**
                    -----------------------------------
                    ``8`` Adult
                    ``10`` Juvenile\s
                    *OR*
                    ``8`` Adult
                    ``5`` Sub-Adult\s
                    ``5`` Juvenile
                                        
                    **VELO**
                    -----------------------------------
                    ``Pure Pack is Unlimited.``
                                        
                    **MEGARAPTOR**
                    -----------------------------------
                    ``6`` Adult
                    ``10`` Juvenile\s
                    *OR*
                    ``6`` Adult
                    ``4`` Sub-Adult\s
                    ``4`` Juvenile
                                        
                                        
                                        
                    :leafy_green:  **APA**
                    -----------------------------------
                    ``6`` Adult
                    ``8`` Juvenile
                    *OR*
                    ``6`` Adult
                    ``4`` Sub-Adult
                    ``4`` Juvenile
                                        
                    **SAI**
                    -----------------------------------
                    ``6`` Adult
                    ``8`` Juvenile
                    *OR*
                    ``6`` Adult
                    ``4`` Sub-Adult
                    ``4`` Juvenile
                                        
                    **COAH**
                    -----------------------------------
                    ``6`` Adult
                    ``8`` Juvenile
                    *OR*
                    ``6`` Adult
                    ``4`` Sub-Adult
                    ``4`` Juvenile
                                        
                    **PARA**
                    -----------------------------------
                    ``Pure and Mixed Pack is Unlimited.``
                                        
                    **LURD**
                    -----------------------------------
                    ``Pure and Mixed Pack is Unlimited.``
                                        
                    **PACHY**
                    -----------------------------------
                    ``Pure and Mixed Pack is Unlimited.``
                                        
                    **ORY**
                    -----------------------------------
                    ``Pure and Mixed Pack is Unlimited.``
                                        
                                        
                                        
                    :shark:  **KRONO**
                    -----------------------------------
                    ``2`` Adult
                    ``3`` Juvenile\s
                    *OR*
                    ``2`` Adult
                    ``1`` Sub-Adult\s
                    ``2`` Juvenile
                                        
                    **MOSA**
                    -----------------------------------
                    ``3`` Adult
                    ``4`` Juvenile\s
                    *OR*
                    ``3`` Adult
                    ``1`` Sub-Adult\s
                    ``3`` Juvenile
                                        
                    **ELAS**
                    -----------------------------------
                    ``6`` Adult
                    ``4`` Juvenile\s
                    *OR*
                    ``5`` Adult
                    ``1`` Sub-Adult\s
                    ``3`` Juvenile
                    """);
            channel.sendMessageEmbeds(embedBuilder.build()).queue();
            event.reply("Successfully sent the message!").setEphemeral(true).queue();
        }
    }

    private static class DisciplineSystem extends SlashCommand {
        public DisciplineSystem() {
            this.name = "discipline_system";
            this.help = "Sends an embed of the discipline system.";
            this.userPermissions = new Permission[]{Permission.MESSAGE_MANAGE};
        }

        @Override
        protected void execute(SlashCommandEvent event) {
            TextChannel channel = event.getGuild().getTextChannelById(948976108777398364L);
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setAuthor("Discipline System", null, event.getGuild().getIconUrl());
            embedBuilder.setFooter("Last updated at ").setTimestamp(OffsetDateTime.now());
            embedBuilder.setDescription("""
                    **DISCIPLINE SYSTEM**
                                        
                                        
                                        
                    ```fix
                    Every player is responsible to read the rules and understand them; for both the discord and the in-game server. Failure to read up on our rules is not an excuse to rulebreak, we expect you to take full responsibility on your actions.
                    ```
                                        
                                        
                    ```excel
                    "Proof:" There must be proof in the form of a screenshot or video in order for punishment to be issued; if you feel this was a mistake, feel free to contest the strike.
                    ```
                    \s
                                        
                    ```excel
                    "Strikes:" Strikes will only be given for first offenders if the rulebreak seemed intentional or careless. If a rulebreak is severe, several strikes may be issued at once.
                        • 1 Strike - Warning/Rulebreak explanation
                        • 2 Strikes - Warning/Kick
                        • 3 Strikes - Temporary ban
                        • 4 Strikes - Permanent ban
                    ```
                    \s
                                        
                    ```fix
                    Strike Removal: If a player goes 2 months of good behavior, 1 strike will be removed.\s
                    ```
                                        
                                        
                    ```fix
                    Hackers: Hackers will be permabanned on first offense without a chance to appeal.
                    ```
                                        
                                        
                    ```excel
                    "Appealing a Ban:" You may appeal your ban if you feel it was unfair by messaging a Admin or Moderator. If you can show that you understand what you did incorrectly and are remorseful, your ban may be reversed; everything varies on the situation, and what we see in you as a member.
                     ```
                    """);
            channel.sendMessageEmbeds(embedBuilder.build()).queue();
            event.reply("Successfully sent the message!").setEphemeral(true).queue();
        }
    }

    private static class ReactionRole extends SlashCommand {
        public ReactionRole() {
            this.name = "reaction_roles";
            this.help = "Sends an embed of the reaction roles.";
            this.userPermissions = new Permission[]{Permission.MESSAGE_MANAGE};
        }

        @Override
        protected void execute(SlashCommandEvent event) {
            Long[] roleIds = new Long[]{948981748891529296L, 948981929565380610L, 949010117624496209L, 949010041002934283L, 949010292367560715L, 948981262922711080L, 948981130269429830L, 948981605878345758L, 952380907321430046L};
            int x = 0;
            SelectMenu.Builder menu = SelectMenu.create("roles")
                    .setPlaceholder("Choose your role") // shows the placeholder indicating what this menu is for
                    .setRequiredRange(1, roleIds.length);
            StringBuilder stringBuilder = new StringBuilder();
            while (x < roleIds.length) {
                String roleName = event.getGuild().getRoleById(roleIds[x]).getName();
                stringBuilder.append("<@&").append(roleIds[x]).append(">")
                        .append(" - To get the ")
                        .append(roleName).append(" role\n");
                menu.addOption(roleName, String.valueOf(roleIds[x]));
                x++;
            }
            TextChannel channel = event.getGuild().getTextChannelById(948976164813307944L);
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setDescription("""
            __**Reaction Roles**__
            Please select the following roles to get pinged when something new happens.
            
            """ + stringBuilder);

            channel.sendMessageEmbeds(embedBuilder.build()).setActionRow(menu.build()).queue();
            event.reply("Successfully sent the message!").setEphemeral(true).queue();
        }
    }
}
