package l2f.gameserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import gnu.trove.map.hash.TIntIntHashMap;
import l2f.commons.configuration.ExProperties;
import l2f.commons.net.AdvIP;
import l2f.commons.net.nio.impl.SelectorConfig;
import l2f.gameserver.data.htm.HtmCache;
import l2f.gameserver.model.actor.instances.player.Bonus;
import l2f.gameserver.model.base.Experience;
import l2f.gameserver.model.base.PlayerAccess;
import l2f.gameserver.network.loginservercon.ServerType;
import l2f.gameserver.network.serverpackets.components.ChatType;
import l2f.gameserver.utils.AddonsConfig;
import l2f.gameserver.utils.GArray;
import l2f.gameserver.utils.Location;

public class Config
{
	private static final Logger _log = LoggerFactory.getLogger(Config.class);

	public static final int NCPUS = Runtime.getRuntime().availableProcessors();
	/** Configuration files */
	public static final String OTHER_CONFIG_FILE = "config/mod/other.ini";
	public static final String RESIDENCE_CONFIG_FILE = "config/residence.ini";
	public static final String SPOIL_CONFIG_FILE = "config/spoil.ini";
	public static final String ALT_SETTINGS_FILE = "config/altsettings.ini";
	public static final String FORMULAS_CONFIGURATION_FILE = "config/formulas.ini";
	public static final String PVP_CONFIG_FILE = "config/pvp.ini";
	public static final String TELNET_CONFIGURATION_FILE = "config/telnet.ini";
	public static final String CONFIGURATION_FILE = "config/server.ini";
	public static final String AI_CONFIG_FILE = "config/ai.ini";
	public static final String GEODATA_CONFIG_FILE = "config/geodata.ini";
	public static final String EVENTS_CONFIG_FILE = "config/events/events.ini";
	public static final String SERVICES_FILE = "config/services/services.ini";
	public static final String OLYMPIAD = "config/olympiad.ini";
	public static final String DEVELOP_FILE = "config/develop.ini";
	public static final String EXT_FILE = "config/ext.ini";
	public static final String RATES_FILE = "config/rates.ini";
	public static final String WEDDING_FILE = "config/services/Wedding.ini";
	public static final String CHAT_FILE = "config/chat.ini";
	public static final String NPC_FILE = "config/npc.ini";
	public static final String BOSS_FILE = "config/boss.ini";
	public static final String FAKE_PLAYERS_LIST = "config/fake_players.list";
	public static final String PREMIUM_FILE = "config/services/premium.ini";
	public static final String TOP_FILE = "config/services/tops.ini";
	public static final String EPIC_BOSS_FILE = "config/epic.ini";
	public static final String ITEM_USE_FILE = "config/UseItems.ini";
	public static final String INSTANCES_FILE = "config/instances.ini";
	public static final String ITEMS_FILE = "config/items.ini";
	public static final String ANUSEWORDS_CONFIG_FILE = "config/abusewords.txt";
	public static final String ADV_IP_FILE = "config/advipsystem.ini";
	public static final String NPCBUFFER_CONFIG_FILE = "config/npcbuffer.ini";
	public static final String DONATE_CONFIG_FILE = "config/DonatorManager.ini";
	public static final String DONATION_STORE = "config/services/DonationStore.ini";
	public static final String GM_PERSONAL_ACCESS_FILE = "config/GMAccess.xml";
	public static final String GM_ACCESS_FILES_DIR = "config/GMAccess.d/";
	public static final String COMMANDS_CONFIG_FILE = "config/mod/commands.ini";
	public static final String TALKING_GUARD_CONFIG_FILE = "config/mod/TalkingGuard.ini";
	public static final String BUFFER_CONFIG_FILE = "config/services/Buffer.ini";
	public static final String ACC_MOVE_FILE = "config/services/CharMove.ini";
	public static final String EVENT_APRIL_FOOLS_CONFIG_FILE = "config/events/AprilFools.ini";
	public static final String EVENT_CHANGE_OF_HEART_CONFIG_FILE = "config/events/ChangeOfHeart.ini";
	public static final String EVENT_COFFER_OF_SHADOWS_CONFIG_FILE = "config/events/CofferOfShadows.ini";
	public static final String EVENT_FIGHT_CLUB_FILE = "config/events/FightClub.ini";
	public static final String EVENT_GLITTERING_MEDAL_CONFIG_FILE = "config/events/GlitteringMedal.ini";
	public static final String EVENT_L2_DAY_CONFIG_FILE = "config/events/L2Day.ini";
	public static final String EVENT_MARCH_8_CONFIG_FILE = "config/events/March8.ini";
	public static final String EVENT_MASTER_OF_ENCHANING_CONFIG_FILE = "config/events/MasterOfEnchaning.ini";
	public static final String EVENT_SAVING_SNOWMAN_CONFIG_FILE = "config/events/SavingSnowman.ini";
	public static final String EVENT_THE_FALL_HARVEST_CONFIG_FILE = "config/events/TheFallHarvest.ini";
	public static final String EVENT_TRICK_OF_TRANSMUTATION_CONFIG_FILE = "config/events/TrickOfTransmutation.ini";
	public static final String EVENT_WORLD_DROP_CONFIG_FILE = "config/events/WorldDrop.ini";
	public static final String PC_CONFIG_FILE = "config/events/PcBangPoints.ini";
	public static final String PVP_MOD_CONFIG_FILE = "config/mod/PvPmod.ini";
	public static final String ZONE_DRAGONVALLEY_FILE = "config/zones/DragonValley.ini";
	public static final String ZONE_LAIROFANTHARAS_FILE = "config/zones/LairOfAntharas.ini";
	public static final String BOARD_MANAGER_CONFIG_FILE = "config/CommunityPvP/board_manager.ini";
	public static final String CLASS_MASTER_CONFIG_FILE = "config/CommunityPvP/class_master.ini";
	public static final String SHOP_MANAGER_CONFIG_FILE = "config/CommunityPvP/shop_manager.ini";
	public static final String TELEPORT_MANAGER_CONFIG_FILE = "config/CommunityPvP/teleport_manager.ini";
	public static final String ENCHANT_CB_CONFIG_FILE = "config/CommunityPvP/EnchantCB.ini";
	public static final String RANKING_CB_CONFIG_FILE = "config/CommunityPvP/ranking.ini";
	public static final String AUGMENT_CB_CONFIG_FILE = "config/CommunityPvP/augment.ini";
	public static final String ACADEMY_CB_CONFIG_FILE = "config/CommunityPvP/academy.ini";
	public static final String RAID_EVENT_CONFIG_FILE = "config/events/RaidEvent.ini";
	public static final String BUFF_STORE_CONFIG_FILE = "config/mod/OfflineBuffer.ini";
	public static final String FORGE_CONFIG_FILE = "config/services/forge.ini";
	public static final String ANTI_FEED_CONFIG_FILE = "config/mod/AntiFeed.ini";
	public static final String DONATE_REWARD_CONFIG_FILE = "config/services/DonateReward.ini";
	public static final String ACHIEVEMENTS_CONFIG_FILE = "config/mod/Achievements.ini";
	public static final String CAPTCHA_CONFIG_FILE = "config/mod/Captcha.ini";
	public static final String UNDERGROUND_COLISEUM_CONFIG_FILE = "config/events/UndergroundColiseum.ini";
	public static final String DAILY_QUESTS_CONFIG_FILE = "config/mod/DailyQuests.ini";
	public static final String SEVEN_DAYS_REWARD_CONFIG_FILE = "config/mod/SevenDaysReward.properties";
	public static final String PHANTOM_FILE = "config/phantom/Phantoms.ini";
	public static int HTM_CACHE_MODE;
	public static boolean LOG_SERVICES;
	public static boolean ALLOW_ADDONS_CONFIG;
	public static boolean ALLOW_IP_LOCK;
	public static boolean ALLOW_HWID_LOCK;
	public static int HWID_LOCK_MASK;
	public static boolean ENABLE_SECONDARY_PASSWORD;
	public static String MAIL_USER;
	public static String MAIL_PASS;
	public static String MAIL_SUBJECT;
	public static String MAIL_MESSAGE;
	/** GameServer ports */
	public static int[] PORTS_GAME;
	public static String GAMESERVER_HOSTNAME;
	public static boolean useNewProxies;
	public static boolean ADVIPSYSTEM;
	public static List<AdvIP> GAMEIPS = new ArrayList<AdvIP>();
	public static String DATABASE_DRIVER;
	public static int DATABASE_MAX_CONNECTIONS;
	public static int DATABASE_MAX_IDLE_TIMEOUT;
	public static int DATABASE_IDLE_TEST_PERIOD;
	public static String DATABASE_GAME_URL;
	public static String DATABASE_GAME_USER;
	public static String DATABASE_GAME_PASSWORD;
	public static String DATABASE_LOGIN_URL;
	public static String DATABASE_LOGIN_USER;
	public static String DATABASE_LOGIN_PASSWORD;
	public static String MYSQL_DUMP_PATH;
	public static boolean AUTOSAVE;
	public static long USER_INFO_INTERVAL;
	public static long BROADCAST_CHAR_INFO_INTERVAL;
	public static int EFFECT_TASK_MANAGER_COUNT;
	public static int MAXIMUM_ONLINE_USERS;
	public static int ONLINE_PLUS;
	public static boolean DONTLOADSPAWN;
	public static boolean DONTLOADQUEST;
	public static int MAX_REFLECTIONS_COUNT;
	public static int SHIFT_BY;
	public static int SHIFT_BY_Z;
	public static int MAP_MIN_Z;
	public static int MAP_MAX_Z;
	public static boolean SERVICES_CLAN_PROMOTION_ENABLE;
	public static int SERVICES_CLAN_PROMOTION_MAX_LEVEL;
	public static int SERVICES_CLAN_PROMOTION_MIN_ONLINE;
	public static int SERVICES_CLAN_PROMOTION_ITEM;
	public static int SERVICES_CLAN_PROMOTION_ITEM_COUNT;
	public static int SERVICES_CLAN_PROMOTION_SET_LEVEL;
	public static int SERVICES_CLAN_PROMOTION_ADD_REP;
	public static boolean SERVICE_CLAN_PRMOTION_ADD_EGGS;
	public static String[] CLAN_PROMOTION_CLAN_EGGS;
	/** ChatBan */
	public static int CHAT_MESSAGE_MAX_LEN;
	public static boolean ABUSEWORD_BANCHAT;
	public static int[] BAN_CHANNEL_LIST = new int[18];
	public static boolean ABUSEWORD_REPLACE;
	public static String ABUSEWORD_REPLACE_STRING;
	public static int ABUSEWORD_BANTIME;
	public static Pattern[] ABUSEWORD_LIST = {};
	public static boolean BANCHAT_ANNOUNCE;
	public static boolean BANCHAT_ANNOUNCE_FOR_ALL_WORLD;
	public static boolean BANCHAT_ANNOUNCE_NICK;
	public static int[] CHATFILTER_CHANNELS = new int[18];
	public static int CHATFILTER_MIN_LEVEL = 0;
	public static int CHATFILTER_WORK_TYPE = 1;
	public static int CHATS_REQUIRED_LEVEL;
	public static int PM_REQUIRED_LEVEL;
	public static int SHOUT_REQUIRED_LEVEL;
	public static int ALT_MAIL_MIN_LVL;
	public static boolean RECORD_WROTE_CHAT_MSGS_COUNT;
	public static int ANNOUNCE_VOTE_DELAY;
	public static boolean SAVING_SPS;
	public static boolean MANAHEAL_SPS_BONUS;
	public static int ALT_ADD_RECIPES;
	public static int ALT_MAX_ALLY_SIZE;
	public static int ALT_LEVEL_DIFFERENCE_PROTECTION;
	public static int ALT_PARTY_DISTRIBUTION_RANGE;
	public static double[] ALT_PARTY_BONUS;
	public static double ALT_ABSORB_DAMAGE_MODIFIER;
	public static boolean ALT_ALL_PHYS_SKILLS_OVERHIT;
	public static double ALT_POLE_DAMAGE_MODIFIER;
	public static boolean ALT_REMOVE_SKILLS_ON_DELEVEL;
	public static boolean ALT_USE_BOW_REUSE_MODIFIER;
	public static boolean ALT_VITALITY_ENABLED;
	public static double ALT_VITALITY_RATE;
	public static double ALT_VITALITY_CONSUME_RATE;
	public static int ALT_VITALITY_RAID_BONUS;
	public static final int[] VITALITY_LEVELS =
	{
		240,
		2000,
		13000,
		17000,
		20000
	};

	public static int MAX_HWID_INSTANCES;
	public static int MAX_HWID_INSTANCES_PRIVILEGED;
	public static GArray<String> PRIVILEGED_HWID;

	public static boolean SERVICES_CLAN_LEVEL_ENABLED;
	public static int SERVICES_CLAN_LEVEL_ITEM;
	public static int SERVICES_CLAN_LEVEL_8_PRICE;
	public static int SERVICES_CLAN_LEVEL_9_PRICE;
	public static int SERVICES_CLAN_LEVEL_10_PRICE;
	public static int SERVICES_CLAN_LEVEL_11_PRICE;
	public static boolean SERVICES_CLAN_SKILLS_ENABLED;
	public static int SERVICES_CLAN_SKILLS_ITEM;
	public static int SERVICES_CLAN_SKILLS_8_PRICE;
	public static int SERVICES_CLAN_SKILLS_9_PRICE;
	public static int SERVICES_CLAN_SKILLS_10_PRICE;
	public static int SERVICES_CLAN_SKILLS_11_PRICE;
	public static boolean SERVICES_OLF_STORE_ENABLED;
	public static int SERVICES_OLF_STORE_ITEM;
	public static int SERVICES_OLF_STORE_0_PRICE;
	public static int SERVICES_OLF_STORE_6_PRICE;
	public static int SERVICES_OLF_STORE_7_PRICE;
	public static int SERVICES_OLF_STORE_8_PRICE;
	public static int SERVICES_OLF_STORE_9_PRICE;
	public static int SERVICES_OLF_STORE_10_PRICE;
	public static boolean SERVICES_OLF_TRANSFER_ENABLED;
	public static int[] SERVICES_OLF_TRANSFER_ITEM;
	public static boolean SERVICES_SOUL_CLOAK_TRANSFER_ENABLED;
	public static int[] SERVICES_SOUL_CLOAK_TRANSFER_ITEM;
	public static boolean SERVICES_EXCHANGE_EQUIP;
	public static int SERVICES_EXCHANGE_EQUIP_ITEM;
	public static int SERVICES_EXCHANGE_EQUIP_ITEM_PRICE;
	public static int SERVICES_EXCHANGE_UPGRADE_EQUIP_ITEM;
	public static int SERVICES_EXCHANGE_UPGRADE_EQUIP_ITEM_PRICE;
	public static int[] SERVICES_UNBAN_ITEM;
	public static int DONATE_ID;
	public static int DONATOR_NPC_ITEM;
	public static String DONATOR_NPC_ITEM_NAME;
	public static int DONATOR_NPC_COUNT_FAME;
	public static int DONATOR_NPC_FAME;
	public static int DONATOR_NPC_COUNT_REP;
	public static int DONATOR_NPC_REP;
	public static int DONATOR_NPC_COUNT_NOBLESS;
	public static int DONATOR_NPC_COUNT_SEX;
	public static int DONATOR_NPC_COUNT_LEVEL;
	public static boolean DONATE_NOBLESS_ENABLE;
	public static int DONATE_NOBLESS_SELL_ITEM;
	public static long DONATE_NOBLESS_SELL_PRICE;
	public static boolean ALT_TELEPORTS_ONLY_FOR_GIRAN;
	/** Ancient Herb */
	public static int ANCIENT_HERB_SPAWN_RADIUS;
	public static int ANCIENT_HERB_SPAWN_CHANCE;
	public static int ANCIENT_HERB_SPAWN_COUNT;
	public static int ANCIENT_HERB_RESPAWN_TIME;
	public static int ANCIENT_HERB_DESPAWN_TIME;
	public static List<Location> HEIN_FIELDS_LOCATIONS = new ArrayList<Location>();
	public static int BKARIK_D_M_CHANCE;
	public static int NECROMANCER_MS_CHANCE;
	public static double DWARRIOR_MS_CHANCE;
	public static double DHUNTER_MS_CHANCE;
	public static int BDRAKE_MS_CHANCE;
	public static int EDRAKE_MS_CHANCE;
	public static int VITAMIN_PETS_FOOD_ID;
	public static int VITAMIN_DESELOT_FOOD_ID;
	public static int VITAMIN_SUPERPET_FOOD_ID;
	public static boolean ALLOW_PET_ATTACK_MASTER;
	// Scheme Buffer
	public static boolean NpcBuffer_VIP;
	public static int NpcBuffer_VIP_ALV;
	public static boolean NpcBuffer_EnableBuff;
	public static boolean NpcBuffer_EnableScheme;
	public static boolean NpcBuffer_EnableHeal;
	public static boolean NpcBuffer_EnableBuffs;
	public static boolean NpcBuffer_EnableResist;
	public static boolean NpcBuffer_EnableSong;
	public static boolean NpcBuffer_EnableDance;
	public static boolean NpcBuffer_EnableChant;
	public static boolean NpcBuffer_EnableOther;
	public static boolean NpcBuffer_EnableSpecial;
	public static boolean NpcBuffer_EnableCubic;
	public static boolean NpcBuffer_EnableCancel;
	public static boolean NpcBuffer_EnableBuffSet;
	public static boolean NpcBuffer_EnableBuffPK;
	public static boolean NpcBuffer_EnableFreeBuffs;
	public static boolean NpcBuffer_EnableTimeOut;
	public static int NpcBuffer_TimeOutTime;
	public static int NpcBuffer_MinLevel;
	public static int NpcBuffer_PriceCancel;
	public static int NpcBuffer_PriceHeal;
	public static int NpcBuffer_PriceBuffs;
	public static int NpcBuffer_PriceResist;
	public static int NpcBuffer_PriceSong;
	public static int NpcBuffer_PriceDance;
	public static int NpcBuffer_PriceChant;
	public static int NpcBuffer_PriceOther;
	public static int NpcBuffer_PriceSpecial;
	public static int NpcBuffer_PriceCubic;
	public static int NpcBuffer_PriceSet;
	public static int NpcBuffer_PriceScheme;
	public static int NpcBuffer_MaxScheme;
	public static boolean NpcBuffer_EnablePremiumBuffs;
	public static boolean SCHEME_ALLOW_FLAG;
	public static List<int[]> NpcBuffer_BuffSetMage = new ArrayList<int[]>();
	public static List<int[]> NpcBuffer_BuffSetFighter = new ArrayList<int[]>();
	public static List<int[]> NpcBuffer_BuffSetDagger = new ArrayList<int[]>();
	public static List<int[]> NpcBuffer_BuffSetSupport = new ArrayList<int[]>();
	public static List<int[]> NpcBuffer_BuffSetTank = new ArrayList<int[]>();
	public static List<int[]> NpcBuffer_BuffSetArcher = new ArrayList<int[]>();
	/** Community Board PVP */
	public static boolean ALLOW_BBS_WAREHOUSE;
	public static boolean BBS_WAREHOUSE_ALLOW_PK;
	public static boolean BBS_PVP_CB_ENABLED;
	public static boolean BBS_PVP_ALLOW_SELL;
	public static boolean BBS_PVP_ALLOW_BUY;
	public static boolean BBS_PVP_ALLOW_AUGMENT;
	/**  Community Board Auction */
	public static boolean ENABLE_AUCTION_SYSTEM;
	public static long AUCTION_FEE;
	public static int AUCTION_INACTIVITY_DAYS_TO_DELETE;
	public static boolean ALLOW_AUCTION_OUTSIDE_TOWN;
	public static int SECONDS_BETWEEN_ADDING_AUCTIONS;
	public static boolean AUCTION_PRIVATE_STORE_AUTO_ADDED;
	/** Communiy Board Drop Calculator */
	public static boolean ALLOW_DROP_CALCULATOR;
	public static int[] DROP_CALCULATOR_DISABLED_TELEPORT;
	public static boolean ALLOW_SENDING_IMAGES;
	public static int TalkGuardChance;
	public static int TalkNormalChance = 0;
	public static int TalkNormalPeriod = 0;
	public static int TalkAggroPeriod = 0;
	public static boolean SERVICES_RIDE_HIRE_ENABLED;
	public static boolean SERVICES_DELEVEL_ENABLED;
	public static boolean ALLOW_MAIL_OPTION;
	public static int SERVICES_DELEVEL_ITEM;
	public static int SERVICES_DELEVEL_COUNT;
	public static int SERVICES_DELEVEL_MIN_LEVEL;
	public static int SERVICES_HAIR_CHANGE_ITEM_ID;
	public static int SERVICES_HAIR_CHANGE_COUNT;
	public static boolean SERVICES_LEVEL_UP_ENABLE;
	public static int[] SERVICES_LEVEL_UP;
	public static boolean SERVICES_DELEVEL_ENABLE;
	public static int[] SERVICES_DELEVEL;
	public static int SERVICES_CLEAR_INSTANCE_PRICE_ID;
	public static int SERVICES_CLEAR_INSTANCE_PRICE_COUNT;
	public static int SERVER_RANKING_REWARD_ITEM_ID;
	public static int[] SERVER_RANKING_REWARD_ITEM_COUNT;
	public static boolean SERVICES_CHANGE_Title_COLOR_ENABLED;
	public static int SERVICES_CHANGE_Title_COLOR_PRICE;
	public static int SERVICES_CHANGE_Title_COLOR_ITEM;
	public static String[] SERVICES_CHANGE_Title_COLOR_LIST;
	public static int SERVICES_AUGMENTATION_PRICE;
	public static int SERVICES_AUGMENTATION_ITEM;
	public static List<Integer> SERVICES_AUGMENTATION_DISABLED_LIST = new ArrayList<>();
	/** Global Vote System */
	public static boolean ALLOW_HOPZONE_VOTE_REWARD;
	public static String HOPZONE_SERVER_LINK;
	public static String HOPZONE_FIRST_PAGE_LINK;
	public static int HOPZONE_VOTES_DIFFERENCE;
	public static int HOPZONE_FIRST_PAGE_RANK_NEEDED;
	public static int HOPZONE_REWARD_CHECK_TIME;
	public static int HOPZONE_REWARD_ID;
	public static int HOPZONE_REWARD_COUNT;
	public static int HOPZONE_DUALBOXES_ALLOWED;
	public static boolean ALLOW_HOPZONE_GAME_SERVER_REPORT;
	public static boolean ALLOW_TOPZONE_VOTE_REWARD;
	public static String TOPZONE_SERVER_LINK;
	public static String TOPZONE_FIRST_PAGE_LINK;
	public static int TOPZONE_VOTES_DIFFERENCE;
	public static int TOPZONE_FIRST_PAGE_RANK_NEEDED;
	public static int TOPZONE_REWARD_CHECK_TIME;
	public static int TOPZONE_REWARD_ID;
	public static int TOPZONE_REWARD_COUNT;
	public static int TOPZONE_DUALBOXES_ALLOWED;
	public static boolean ALLOW_TOPZONE_GAME_SERVER_REPORT;
	public static Calendar CASTLE_VALIDATION_DATE;
	public static int PERIOD_CASTLE_SIEGE;
	public static boolean RETURN_WARDS_WHEN_TW_STARTS;
	public static boolean ALT_PCBANG_POINTS_ENABLED;
	public static double ALT_PCBANG_POINTS_BONUS_DOUBLE_CHANCE;
	public static int ALT_PCBANG_POINTS_BONUS;
	public static int ALT_PCBANG_POINTS_DELAY;
	public static int ALT_PCBANG_POINTS_MIN_LVL;
	public static boolean ALT_DEBUG_ENABLED;
	public static boolean ALT_DEBUG_PVP_ENABLED;
	public static boolean ALT_DEBUG_PVP_DUEL_ONLY;
	public static boolean ALT_DEBUG_PVE_ENABLED;
	//TODO CHECK
	public static double CRAFT_MASTERWORK_CHANCE;
	public static double CRAFT_DOUBLECRAFT_CHANCE;
	/** Thread pools size */
	public static int SCHEDULED_THREAD_POOL_SIZE;
	public static int EXECUTOR_THREAD_POOL_SIZE;
	public static boolean ENABLE_RUNNABLE_STATS;
	/** Network settings */
	public static SelectorConfig SELECTOR_CONFIG = new SelectorConfig();
	public static boolean AUTO_LOOT;
	public static boolean AUTO_LOOT_HERBS;
	public static boolean AUTO_LOOT_ONLY_ADENA;
	public static boolean AUTO_LOOT_INDIVIDUAL;
	public static boolean AUTO_LOOT_FROM_RAIDS;
	/** Auto-loot for/from players with karma also? */
	public static boolean AUTO_LOOT_PK;
	public static int CNAME_MAXLEN = 32;
	public static String CHAR_NAME_TEMPLATE;
	public static String CLAN_NAME_TEMPLATE;
	public static String CLAN_TITLE_TEMPLATE;
	public static String ALLY_NAME_TEMPLATE;
	/** Global chat state */
	public static boolean GLOBAL_SHOUT;
	public static boolean GLOBAL_TRADE_CHAT;
	public static int CHAT_RANGE;
	public static int SHOUT_OFFSET;
	public static GArray<String> TRADE_WORDS;
	public static boolean TRADE_CHATS_REPLACE;
	public static String SERVER_NAME;
	public static String SHORT_SERVER_NAME;
	public static String SERVER_SUB_NAME;
	/** For test servers - evrybody has admin rights */
	public static boolean EVERYBODY_HAS_ADMIN_RIGHTS;
	public static double ALT_RAID_RESPAWN_MULTIPLIER;
	public static boolean ALT_ALLOW_AUGMENT_ALL;
	public static boolean ALT_ALLOW_DROP_AUGMENTED;
	public static boolean ALT_GAME_UNREGISTER_RECIPE;
	/** Delay for announce SS period (in minutes) */
	public static int SS_ANNOUNCE_PERIOD;
	/** Petition manager */
	public static boolean PETITIONING_ALLOWED;
	public static int MAX_PETITIONS_PER_PLAYER;
	public static int MAX_PETITIONS_PENDING;
	/** Show mob stats/droplist to players? */
	public static boolean ALT_GAME_SHOW_DROPLIST;
	public static boolean ALT_FULL_NPC_STATS_PAGE;
	public static boolean ALLOW_NPC_SHIFTCLICK;
	public static boolean ALT_ALLOW_SELL_COMMON;
	public static boolean ALT_ALLOW_SHADOW_WEAPONS;
	public static int[] ALT_DISABLED_MULTISELL;
	public static int[] ALT_SHOP_PRICE_LIMITS;
	public static int[] ALT_SHOP_UNALLOWED_ITEMS;
	public static int[] ALT_ALLOWED_PET_POTIONS;
	/** Formulas */
	public static double SKILLS_CHANCE_MOD;
	public static double SKILLS_CHANCE_MIN;
	public static double SKILLS_CHANCE_POW;
	public static double SKILLS_CHANCE_CAP;
	public static double SKILLS_MOB_CHANCE;
	public static double SKILLS_DEBUFF_MOB_CHANCE;
	public static boolean SHIELD_SLAM_BLOCK_IS_MUSIC;
	public static boolean ALT_SAVE_UNSAVEABLE;
	public static int ALT_SAVE_EFFECTS_REMAINING_TIME;
	public static boolean ALT_SHOW_REUSE_MSG;
	public static boolean ALT_DELETE_SA_BUFFS;
	public static int SKILLS_CAST_TIME_MIN;
	public static double SKILLS_DELTA_MOD_MULT;
	public static double SKILLS_ATTACKER_WEAPON_MOD;
	public static double SKILLS_M_ATK_MOD_MAX;
	public static double SKILLS_M_ATK_MOD_MIN;
	public static double SKILLS_ELEMENT_MOD_MULT;
	public static double SKILLS_ELEMENT_MOD_MAX;
	public static double SKILLS_ELEMENT_MOD_MIN;
	public static boolean SKILLS_CALC_STAT_MOD;
	/** Allowed items to use */
	public static int[] ITEM_USE_LIST_ID;
	public static boolean ITEM_USE_IS_COMBAT_FLAG;
	public static boolean ITEM_USE_IS_ATTACK;
	public static boolean ITEM_USE_IS_EVENTS;
	public static long MAX_ADENA;
	/** Event Fight Club */
	public static boolean FIGHT_CLUB_ENABLED;
	public static int MINIMUM_LEVEL_TO_PARRICIPATION;
	public static int MAXIMUM_LEVEL_TO_PARRICIPATION;
	public static int MAXIMUM_LEVEL_DIFFERENCE;
	public static String[] ALLOWED_RATE_ITEMS;
	public static int PLAYERS_PER_PAGE;
	public static int ARENA_TELEPORT_DELAY;
	public static boolean CANCEL_BUFF_BEFORE_FIGHT;
	public static boolean UNSUMMON_PETS;
	public static boolean UNSUMMON_SUMMONS;
	public static boolean REMOVE_CLAN_SKILLS;
	public static boolean REMOVE_HERO_SKILLS;
	public static int TIME_TO_PREPARATION;
	public static int FIGHT_TIME;
	public static boolean ALLOW_DRAW;
	public static int TIME_TELEPORT_BACK;
	public static boolean FIGHT_CLUB_ANNOUNCE_RATE;
	public static boolean FIGHT_CLUB_ANNOUNCE_RATE_TO_SCREEN;
	public static boolean FIGHT_CLUB_ANNOUNCE_START_TO_SCREEN;
	public static boolean FIGHT_CLUB_ANNOUNCE_TOP_KILLER;
	public static boolean FIGHT_CLUB_SUMMON_LOSE_BUFFS_ON_DEATH;
	public static boolean EVENT_KOREAN_RESET_REUSE;
	/** Title for new charactersÂ° */
	public static boolean CHAR_TITLE;
	public static String ADD_CHAR_TITLE;
	/** Reuse social action */
	public static boolean ALT_SOCIAL_ACTION_REUSE;
	/** SpellBooks */
	public static boolean ALT_DISABLE_SPELLBOOKS;
	/** Alternative gaming - loss of XP on death */
	public static boolean ALT_GAME_DELEVEL;
	/** AltConfigs */
	public static boolean ALT_ARENA_EXP;
	public static boolean AUTO_SOUL_CRYSTAL_QUEST;
	public static boolean ALT_GAME_SUBCLASS_WITHOUT_QUESTS;
	public static boolean ALT_ALLOW_SUBCLASS_WITHOUT_BAIUM;
	public static int ALT_GAME_START_LEVEL_TO_SUBCLASS;
	public static int ALT_GAME_LEVEL_TO_GET_SUBCLASS;
	public static int ALT_MAX_LEVEL;
	public static int ALT_MAX_SUB_LEVEL;
	public static int ALT_GAME_SUB_ADD;
	public static boolean ALT_GAME_SUB_BOOK;
	public static boolean ALT_NO_LASTHIT;
	public static boolean ALT_KAMALOKA_NIGHTMARES_PREMIUM_ONLY;
	public static boolean ALT_KAMALOKA_NIGHTMARE_REENTER;
	public static boolean ALT_KAMALOKA_ABYSS_REENTER;
	public static boolean ALT_KAMALOKA_LAB_REENTER;
	public static boolean ALT_PET_HEAL_BATTLE_ONLY;
	public static boolean ALT_SIMPLE_SIGNS;
	public static boolean ALT_TELE_TO_CATACOMBS;
	public static boolean ALT_BS_CRYSTALLIZE;
	public static int ALT_MAMMON_EXCHANGE;
	public static int ALT_MAMMON_UPGRADE;
	public static boolean ALT_ALLOW_TATTOO;
	public static int ALT_BUFF_LIMIT;
	public static int MULTISELL_SIZE;
	public static boolean SERVICES_CHANGE_NICK_ENABLED2;
	public static boolean SERVICES_CHANGE_NICK_ALLOW_SYMBOL2;
	public static int SERVICES_CHANGE_NICK_PRICE2;
	public static int SERVICES_CHANGE_NICK_ITEM2;
	public static boolean SERVICES_CHANGE_CLAN_NAME_ENABLED2;
	public static int SERVICES_CHANGE_CLAN_NAME_PRICE2;
	public static int SERVICES_CHANGE_CLAN_NAME_ITEM2;
	public static boolean SERVICES_CHANGE_NICK_ENABLED;
	public static boolean SERVICES_CHANGE_NICK_ALLOW_SYMBOL;
	public static int SERVICES_CHANGE_NICK_PRICE;
	public static int SERVICES_CHANGE_NICK_ITEM;
	public static boolean SERVICES_CHANGE_CLAN_NAME_ENABLED;
	public static int SERVICES_CHANGE_CLAN_NAME_PRICE;
	public static int SERVICES_CHANGE_CLAN_NAME_ITEM;
	public static boolean SERVICES_CHANGE_PET_NAME_ENABLED;
	public static int SERVICES_CHANGE_PET_NAME_PRICE;
	public static int SERVICES_CHANGE_PET_NAME_ITEM;
	public static boolean SERVICES_BUY_RECOMMENDS_ENABLED;
	public static int SERVICES_BUY_RECOMMENDS_PRICE;
	public static int SERVICES_BUY_RECOMMENDS_ITEM;
	public static boolean SERVICES_BUY_CLAN_REPUTATION_ENABLED;
	public static int SERVICES_BUY_CLAN_REPUTATION_PRICE;
	public static int SERVICES_BUY_CLAN_REPUTATION_ITEM;
	public static int SERVICES_BUY_CLAN_REPUTATION_COUNT;
	public static boolean SERVICES_BUY_FAME_ENABLED;
	public static int SERVICES_BUY_FAME_PRICE;
	public static int SERVICES_BUY_FAME_ITEM;
	public static int SERVICES_BUY_FAME_COUNT;
	public static boolean SERVICES_EXCHANGE_BABY_PET_ENABLED;
	public static int SERVICES_EXCHANGE_BABY_PET_PRICE;
	public static int SERVICES_EXCHANGE_BABY_PET_ITEM;
	public static boolean SERVICES_CHANGE_SEX_ENABLED;
	public static int SERVICES_CHANGE_SEX_PRICE;
	public static int SERVICES_CHANGE_SEX_ITEM;
	public static boolean SERVICES_CHANGE_BASE_ENABLED;
	public static int SERVICES_CHANGE_BASE_PRICE;
	public static int SERVICES_CHANGE_BASE_ITEM;
	public static boolean SERVICES_SEPARATE_SUB_ENABLED;
	public static int SERVICES_SEPARATE_SUB_PRICE;
	public static int SERVICES_SEPARATE_SUB_ITEM;
	public static boolean SERVICES_CHANGE_NICK_COLOR_ENABLED;
	public static int SERVICES_CHANGE_NICK_COLOR_PRICE;
	public static int SERVICES_CHANGE_NICK_COLOR_ITEM;
	public static String[] SERVICES_CHANGE_NICK_COLOR_LIST;
	public static boolean SERVICES_BASH_ENABLED;
	public static boolean SERVICES_BASH_SKIP_DOWNLOAD;
	public static int SERVICES_BASH_RELOAD_TIME;
	public static boolean SERVICES_NOBLESS_SELL_ENABLED;
	public static int SERVICES_NOBLESS_SELL_PRICE;
	public static int SERVICES_NOBLESS_SELL_ITEM;
	public static boolean SERVICES_HERO_SELL_ENABLED;
	public static int[] SERVICES_HERO_SELL_DAY;
	public static int[] SERVICES_HERO_SELL_PRICE;
	public static int[] SERVICES_HERO_SELL_ITEM;
	public static boolean SERVICES_HERO_SELL_CHAT;
	public static boolean SERVICES_HERO_SELL_SKILL;
	public static boolean SERVICES_HERO_SELL_ITEMS;
	public static boolean SERVICES_WASH_PK_ENABLED;
	public static int SERVICES_WASH_PK_ITEM;
	public static int SERVICES_WASH_PK_PRICE;
	public static int SERVICES_WASH_KARMA_ITEM;
	public static int SERVICES_WASH_KARMA_PRICE;
	// Service PK Clear from community board.
	public static int SERVICES_CLEAR_PK_PRICE;
	public static int SERVICES_CLEAR_PK_PRICE_ITEM_ID;
	public static int SERVICES_CLEAR_PK_COUNT;
	public static boolean SERVICES_TRANSFER_WEAPON_ENABLED;
	public static int SERVICES_TRANSFER_WEAPON_ITEM;
	public static int SERVICES_TRANSFER_WEAPON_PRICE;
	public static boolean SERVICES_EXPAND_INVENTORY_ENABLED;
	public static int SERVICES_EXPAND_INVENTORY_PRICE;
	public static int SERVICES_EXPAND_INVENTORY_ITEM;
	public static int SERVICES_EXPAND_INVENTORY_MAX;
	public static boolean SERVICES_EXPAND_WAREHOUSE_ENABLED;
	public static int SERVICES_EXPAND_WAREHOUSE_PRICE;
	public static int SERVICES_EXPAND_WAREHOUSE_ITEM;
	public static boolean SERVICES_EXPAND_CWH_ENABLED;
	public static int SERVICES_EXPAND_CWH_PRICE;
	public static int SERVICES_EXPAND_CWH_ITEM;
	public static String SERVICES_SELLPETS;
	public static boolean SERVICES_OFFLINE_TRADE_ALLOW;
	public static boolean SERVICES_OFFLINE_TRADE_ALLOW_OFFSHORE;
	public static int SERVICES_OFFLINE_TRADE_MIN_LEVEL;
	public static int SERVICES_OFFLINE_TRADE_NAME_COLOR;
	public static int SERVICES_OFFLINE_TRADE_PRICE;
	public static int SERVICES_OFFLINE_TRADE_PRICE_ITEM;
	public static long SERVICES_OFFLINE_TRADE_SECONDS_TO_KICK;
	public static boolean SERVICES_OFFLINE_TRADE_RESTORE_AFTER_RESTART;
	public static boolean SERVICES_GIRAN_HARBOR_ENABLED;
	public static boolean SERVICES_PARNASSUS_ENABLED;
	public static boolean SERVICES_PARNASSUS_NOTAX;
	public static long SERVICES_PARNASSUS_PRICE;
	public static boolean SERVICES_ALLOW_LOTTERY;
	public static int SERVICES_LOTTERY_PRIZE;
	public static int SERVICES_ALT_LOTTERY_PRICE;
	public static int SERVICES_LOTTERY_TICKET_PRICE;
	public static double SERVICES_LOTTERY_5_NUMBER_RATE;
	public static double SERVICES_LOTTERY_4_NUMBER_RATE;
	public static double SERVICES_LOTTERY_3_NUMBER_RATE;
	public static int SERVICES_LOTTERY_2_AND_1_NUMBER_PRIZE;
	public static boolean SERVICES_ALLOW_ROULETTE;
	public static long SERVICES_ROULETTE_MIN_BET;
	public static long SERVICES_ROULETTE_MAX_BET;
	public static boolean ALT_ALLOW_OTHERS_WITHDRAW_FROM_CLAN_WAREHOUSE;
	public static boolean ALT_ALLOW_CLAN_COMMAND_ONLY_FOR_CLAN_LEADER;
	public static boolean ALT_GAME_REQUIRE_CLAN_CASTLE;
	public static boolean ALT_GAME_REQUIRE_CASTLE_DAWN;
	public static boolean ALT_GAME_ALLOW_ADENA_DAWN;
	public static boolean RETAIL_SS;
	public static int ATT_MOD_ARMOR;
	public static int ATT_MOD_WEAPON;
	public static int ATT_MOD_WEAPON1;
	public static int ATT_MOD_MAX_ARMOR;
	public static int ATT_MOD_MAX_WEAPON;
	public static boolean SPAWN_CITIES_TREE;
	public static boolean SPAWN_NPC_BUFFER;
	public static int MAX_PARTY_SIZE;
	public static boolean SPAWN_scrubwoman;
	public static boolean ADEPT_ENABLE;
	public static int HENNA_STATS;
	public static boolean ENEBLE_TITLE_COLOR_MOD;
	public static String TYPE_TITLE_COLOR_MOD;
	public static int COUNT_TITLE_1;
	public static int TITLE_COLOR_1;
	public static int COUNT_TITLE_2;
	public static int TITLE_COLOR_2;
	public static int COUNT_TITLE_3;
	public static int TITLE_COLOR_3;
	public static int COUNT_TITLE_4;
	public static int TITLE_COLOR_4;
	public static int COUNT_TITLE_5;
	public static int TITLE_COLOR_5;
	public static boolean ENEBLE_NAME_COLOR_MOD;
	public static String TYPE_NAME_COLOR_MOD;
	public static int COUNT_NAME_1;
	public static int NAME_COLOR_1;
	public static int COUNT_NAME_2;
	public static int NAME_COLOR_2;
	public static int COUNT_NAME_3;
	public static int NAME_COLOR_3;
	public static int COUNT_NAME_4;
	public static int NAME_COLOR_4;
	public static int COUNT_NAME_5;
	public static int NAME_COLOR_5;
	// Killing Spree
	public static boolean KILLING_SPREE_ENABLED;
	public static Map<Integer, String> KILLING_SPREE_ANNOUNCEMENTS;
	public static Map<Integer, String> KILLING_SPREE_COLORS;
	// PvP Configs
	public static boolean NEW_CHAR_IS_NOBLE;
	public static boolean NEW_CHAR_IS_HERO;
	public static boolean ANNOUNCE_SPAWN_RB;
	// Acc move
	public static boolean ACC_MOVE_ENABLED;
	public static int ACC_MOVE_ITEM;
	public static int ACC_MOVE_PRICE;
	// Buffer
	public static boolean BUFFER_ON;
	public static int ITEM_ID;
	public static boolean BUFFER_PET_ENABLED;
	public static int BUFFER_PRICE;
	public static int BUFFER_MIN_LVL;
	public static int BUFFER_MAX_LVL;
	public static boolean BUFFER_ALLOW_IN_INSTANCE;
	/* .km-all-to-me */
	public static boolean ENABLE_KM_ALL_TO_ME;
	/* .res */
	public static boolean COMMAND_RES;
	public static int ITEM_ID_RESS;
	public static int PRICE_RESS;
	/* .dressme */
	public static boolean COMMAND_DRESSME_ENABLE;
	/* .pa */
	public static boolean COMMAND_PA;
	/* .loot */
	public static boolean COMMAND_LOOT;
	/* .lock */
	public static boolean SERVICES_LOCK_ACCOUNT_IP;
	/* .farm */
	public static boolean COMMAND_FARM;
	public static int FARM_TELEPORT_ITEM_ID;
	public static int PRICE_FARM;
	public static int FARM_X;
	public static int FARM_Y;
	public static int FARM_Z;
	/* .farm_hard */
	public static boolean COMMAND_FARM_HARD;
	public static int FARM_HARD_TELEPORT_ITEM_ID;
	public static int PRICE_FARM_HARD;
	public static int FARM_HARD_X;
	public static int FARM_HARD_Y;
	public static int FARM_HARD_Z;
	/* .farm_low */
	public static boolean COMMAND_FARM_LOW;
	public static int FARM_LOW_TELEPORT_ITEM_ID;
	public static int PRICE_FARM_LOW;
	public static int FARM_LOW_X;
	public static int FARM_LOW_Y;
	public static int FARM_LOW_Z;
	/* .pvp */
	public static boolean COMMAND_PVP;
	public static int PVP_TELEPORT_ITEM_ID;
	public static int PRICE_PVP;
	public static int PVP_X;
	public static int PVP_Y;
	public static int PVP_Z;
	public static boolean ALLOW_VOICED_COMMANDS;
	// CustomSpawnNewChar
	public static boolean SPAWN_CHAR;
	public static int SPAWN_X;
	public static int SPAWN_Y;
	public static int SPAWN_Z;
	/** Olympiad Compitition Starting time */
	public static int ALT_OLY_START_TIME;
	/** Olympiad Compition Min */
	public static int ALT_OLY_MIN;
	/** Olympaid Comptetition Period */
	public static long ALT_OLY_CPERIOD;
	/** Olympiad Manager Shout Just One Time CUSTOM MESSAGE */
	public static boolean OLYMPIAD_SHOUT_ONCE_PER_START;
	/** Olympaid Weekly Period */
	public static long ALT_OLY_WPERIOD;
	/** Olympaid Validation Period */
	public static long ALT_OLY_VPERIOD;
	public static boolean ALT_OLYMP_PERIOD;
	public static List<Integer> ALT_OLY_DATE_END_MONTHLY = new ArrayList<>();
	public static int ALT_OLY_DATE_END_WEEKLY;
	public static boolean ENABLE_OLYMPIAD;
	public static boolean ENABLE_OLYMPIAD_SPECTATING;
	public static int CLASS_GAME_MIN;
	public static int NONCLASS_GAME_MIN;
	public static int TEAM_GAME_MIN;
	public static int GAME_MAX_LIMIT;
	public static int GAME_CLASSES_COUNT_LIMIT;
	public static int GAME_NOCLASSES_COUNT_LIMIT;
	public static int GAME_TEAM_COUNT_LIMIT;
	public static int ALT_OLY_REG_DISPLAY;
	public static int ALT_OLY_BATTLE_REWARD_ITEM;
	public static int ALT_OLY_CLASSED_RITEM_C;
	public static int ALT_OLY_NONCLASSED_RITEM_C;
	public static int ALT_OLY_TEAM_RITEM_C;
	public static int ALT_OLY_COMP_RITEM;
	public static int ALT_OLY_GP_PER_POINT;
	public static int ALT_OLY_HERO_POINTS;
	public static int ALT_OLY_RANK1_POINTS;
	public static int ALT_OLY_RANK2_POINTS;
	public static int ALT_OLY_RANK3_POINTS;
	public static int ALT_OLY_RANK4_POINTS;
	public static int ALT_OLY_RANK5_POINTS;
	public static int OLYMPIAD_STADIAS_COUNT;
	public static int OLYMPIAD_BATTLES_FOR_REWARD;
	public static int OLYMPIAD_POINTS_DEFAULT;
	public static int OLYMPIAD_POINTS_WEEKLY;
	public static boolean OLYMPIAD_OLDSTYLE_STAT;
	public static int ALT_OLY_WAIT_TIME;
	public static int ALT_OLY_PORT_BACK_TIME;
	public static boolean OLYMPIAD_PLAYER_IP;
	public static int OLYMPIAD_BEGIN_TIME;
	public static boolean OLYMPIAD_BAD_ENCHANT_ITEMS_ALLOW;
	public static boolean OLY_SHOW_OPPONENT_PERSONALITY;
	public static boolean OLY_ENCH_LIMIT_ENABLE;
	public static int OLY_ENCHANT_LIMIT_WEAPON;
	public static int OLY_ENCHANT_LIMIT_ARMOR;
	public static int OLY_ENCHANT_LIMIT_JEWEL;
	public static long NONOWNER_ITEM_PICKUP_DELAY;
	public static long NONOWNER_ITEM_PICKUP_DELAY_RAIDS;
	/** Logging Chat Window */
	public static boolean LOG_CHAT;
	public static Map<Integer, PlayerAccess> gmlist = new HashMap<Integer, PlayerAccess>();
	/** Rate control */
	public static double RATE_XP;
	public static double RATE_SP;
	public static double RATE_QUESTS_REWARD;
	public static double RATE_QUESTS_DROP;
	public static double RATE_CLAN_REP_SCORE;
	public static int RATE_CLAN_REP_SCORE_MAX_AFFECTED;
	public static double RATE_DROP_ADENA;
	public static double RATE_DROP_CHAMPION;
	public static double RATE_CHAMPION_DROP_ADENA;
	public static double RATE_DROP_SPOIL_CHAMPION;
	public static double RATE_DROP_ITEMS;
	public static double RATE_CHANCE_GROUP_DROP_ITEMS;
	public static double RATE_CHANCE_DROP_ITEMS;
	public static double RATE_CHANCE_DROP_HERBS;
	public static double RATE_CHANCE_SPOIL;
	public static double RATE_CHANCE_SPOIL_WEAPON_ARMOR_ACCESSORY;
	public static double RATE_CHANCE_DROP_WEAPON_ARMOR_ACCESSORY;
	public static double RATE_CHANCE_DROP_EPOLET;
	public static boolean CHAMPION_DROP_ONLY_ADENA;
	public static boolean NO_RATE_KEY_MATERIAL;
	public static boolean NO_RATE_RECIPES;
	public static double RATE_DROP_COMMON_ITEMS;
	public static double RATE_DROP_RAIDBOSS;
	public static double RATE_DROP_SPOIL;
	public static int[] NO_RATE_ITEMS;
	public static GArray<String> NO_DROP_ITEMS;
	public static double RATE_DROP_SIEGE_GUARD;
	public static double RATE_MANOR;
	public static double RATE_FISH_DROP_COUNT;
	public static boolean RATE_PARTY_MIN;
	public static double RATE_HELLBOUND_CONFIDENCE;
	public static boolean NO_RATE_EQUIPMENT;
	public static int RATE_MOB_SPAWN;
	public static int RATE_MOB_SPAWN_MIN_LEVEL;
	public static int RATE_MOB_SPAWN_MAX_LEVEL;
	/** Player Drop Rate control */
	public static boolean KARMA_DROP_GM;
	public static boolean KARMA_NEEDED_TO_DROP;
	public static int KARMA_DROP_ITEM_LIMIT;
	public static int KARMA_RANDOM_DROP_LOCATION_LIMIT;
	public static double KARMA_DROPCHANCE_BASE;
	public static double KARMA_DROPCHANCE_MOD;
	public static double NORMAL_DROPCHANCE_BASE;
	public static int DROPCHANCE_EQUIPMENT;
	public static int DROPCHANCE_EQUIPPED_WEAPON;
	public static int DROPCHANCE_ITEM;
	public static int AUTODESTROY_ITEM_AFTER;
	public static int AUTODESTROY_PLAYER_ITEM_AFTER;
	public static int DELETE_DAYS;
	/** Datapack root directory */
	public static File DATAPACK_ROOT;
	public static double CLANHALL_BUFFTIME_MODIFIER;
	public static double SONGDANCETIME_MODIFIER;
	public static double MAXLOAD_MODIFIER;
	public static double GATEKEEPER_MODIFIER;
	public static boolean ALT_IMPROVED_PETS_LIMITED_USE;
	public static int GATEKEEPER_FREE;
	public static int CRUMA_GATEKEEPER_LVL;
	public static double ALT_CHAMPION_CHANCE1;
	public static double ALT_CHAMPION_CHANCE2;
	public static boolean ALT_CHAMPION_CAN_BE_AGGRO;
	public static boolean ALT_CHAMPION_CAN_BE_SOCIAL;
	public static boolean ALT_CHAMPION_DROP_HERBS;
	public static boolean ALT_SHOW_MONSTERS_LVL;
	public static boolean ALT_SHOW_MONSTERS_AGRESSION;
	public static int ALT_CHAMPION_TOP_LEVEL;
	public static int ALT_CHAMPION_MIN_LEVEL;
	public static boolean ALLOW_DISCARDITEM;
	public static boolean ALLOW_DISCARDITEM_AT_PEACE;
	public static boolean ALLOW_MAIL;
	public static boolean ALLOW_WAREHOUSE;
	public static boolean ALLOW_WATER;
	public static boolean ALLOW_CURSED_WEAPONS;
	public static boolean DROP_CURSED_WEAPONS_ON_KICK;
	public static long PACKET_FLOOD_PROTECTION_IN_MS;
	public static long ENTER_WORLD_FLOOD_PROECTION_IN_MS;
	public static boolean ALLOW_NOBLE_TP_TO_ALL;
	public static boolean ALLOW_ENTER_INSTANCE;
	public static boolean ALLOW_PRIVATE_STORES;
	public static boolean ALLOW_TALK_TO_NPCS;
	public static boolean ALLOW_JUST_MOVING;
	public static boolean ALLOW_TUTORIAL;
	public static boolean ALLOW_HWID_ENGINE;
	public static boolean ALLOW_SKILLS_STATS_LOGGER;
	public static boolean ALLOW_ITEMS_LOGGING;
	public static boolean ALLOW_SPAWN_PROTECTION;
	public static boolean SELL_ALL_ITEMS_FREE;
	/** Pets */
	public static int SWIMING_SPEED;
	/** protocol revision */
	public static int MIN_PROTOCOL_REVISION;
	public static int MAX_PROTOCOL_REVISION;
	/** random animation interval */
	public static int MIN_NPC_ANIMATION;
	public static int MAX_NPC_ANIMATION;
	public static String DEFAULT_LANG;
	public static String DEFAULT_GK_LANG;
	public static double[] AUGMENTATION_CHANCE_MOD;
	public static String RESTART_AT_TIME;
	public static boolean BACKUP_DURING_AUTO_RESTART;
	public static int[][] ADDITIONAL_BACKUP_DATES;
	public static int GAME_SERVER_LOGIN_PORT;
	public static boolean GAME_SERVER_LOGIN_CRYPT;
	public static String GAME_SERVER_LOGIN_HOST;
	public static String INTERNAL_HOSTNAME;
	public static String EXTERNAL_HOSTNAME;
	public static boolean SECOND_AUTH_ENABLED;
	public static boolean SECOND_AUTH_BAN_ACC;
	public static boolean SECOND_AUTH_STRONG_PASS;
	public static int SECOND_AUTH_MAX_ATTEMPTS;
	public static long SECOND_AUTH_BAN_TIME;
	public static String SECOND_AUTH_REC_LINK;
	public static boolean SERVER_SIDE_NPC_NAME;
	public static boolean SERVER_SIDE_NPC_TITLE;
	public static boolean SERVER_SIDE_NPC_TITLE_ETC;
	public static String CLASS_MASTERS_PRICE;
	public static int CLASS_MASTERS_PRICE_ITEM;
	public static List<Integer> ALLOW_CLASS_MASTERS_LIST = new ArrayList<Integer>();
	public static int[] CLASS_MASTERS_PRICE_LIST = new int[4];
	public static boolean ALLOW_EVENT_GATEKEEPER;
	public static boolean ITEM_BROKER_ITEM_SEARCH;
	/** Inventory slots limits */
	public static int INVENTORY_MAXIMUM_NO_DWARF;
	public static int INVENTORY_MAXIMUM_DWARF;
	public static int INVENTORY_MAXIMUM_GM;
	public static int QUEST_INVENTORY_MAXIMUM;
	/** Warehouse slots limits */
	public static int WAREHOUSE_SLOTS_NO_DWARF;
	public static int WAREHOUSE_SLOTS_DWARF;
	public static int WAREHOUSE_SLOTS_CLAN;
	public static int FREIGHT_SLOTS;
	/** Spoil Rates */
	public static double BASE_SPOIL_RATE;
	public static double MINIMUM_SPOIL_RATE;
	public static boolean ALT_SPOIL_FORMULA;
	/** Manor Config */
	public static double MANOR_SOWING_BASIC_SUCCESS;
	public static double MANOR_SOWING_ALT_BASIC_SUCCESS;
	public static double MANOR_HARVESTING_BASIC_SUCCESS;
	public static int MANOR_DIFF_PLAYER_TARGET;
	public static double MANOR_DIFF_PLAYER_TARGET_PENALTY;
	public static int MANOR_DIFF_SEED_TARGET;
	public static double MANOR_DIFF_SEED_TARGET_PENALTY;
	/** Karma System Variables */
	public static int KARMA_MIN_KARMA;
	public static int KARMA_SP_DIVIDER;
	public static int KARMA_LOST_BASE;
	public static int MIN_PK_TO_ITEMS_DROP;
	public static boolean DROP_ITEMS_ON_DIE;
	public static boolean DROP_ITEMS_AUGMENTED;
	public static List<Integer> KARMA_LIST_NONDROPPABLE_ITEMS = new ArrayList<Integer>();
	public static int PVP_TIME;
	/** Karma Punishment */
	public static boolean ALT_GAME_KARMA_PLAYER_CAN_SHOP;
	/** Chance that an item will succesfully be enchanted */
	public static long ENCHANT_REUSE;
	public static boolean ENCHANT_SPAM_KICK;
	public static boolean ENCHANT_SPAM_LOGS;
	public static int ENCHANT_CHANCE_WEAPON;
	public static int ENCHANT_CHANCE_ARMOR;
	public static int ENCHANT_CHANCE_ACCESSORY;
	public static int ENCHANT_CHANCE_CRYSTAL_WEAPON;
	public static int ENCHANT_CHANCE_CRYSTAL_ARMOR;
	public static int ENCHANT_CHANCE_CRYSTAL_ARMOR_OLF;
	public static int ENCHANT_CHANCE_CRYSTAL_ACCESSORY;
	public static int ENCHANT_CHANCE_WEAPON_BLESS;
	public static int ENCHANT_CHANCE_ARMOR_BLESS;
	public static int ENCHANT_CHANCE_ACCESSORY_BLESS;
	public static boolean USE_ALT_ENCHANT;
	public static boolean OLF_TSHIRT_CUSTOM_ENABLED;
	public static ArrayList<Integer> ENCHANT_WEAPON_FIGHT = new ArrayList<Integer>();
	public static ArrayList<Integer> ENCHANT_WEAPON_FIGHT_CRYSTAL = new ArrayList<Integer>();
	public static ArrayList<Integer> ENCHANT_WEAPON_FIGHT_BLESSED = new ArrayList<Integer>();
	public static ArrayList<Integer> ENCHANT_ARMOR = new ArrayList<Integer>();
	public static ArrayList<Integer> ENCHANT_ARMOR_CRYSTAL = new ArrayList<Integer>();
	public static ArrayList<Integer> ENCHANT_ARMOR_BLESSED = new ArrayList<Integer>();
	public static ArrayList<Integer> ENCHANT_ARMOR_JEWELRY = new ArrayList<Integer>();
	public static ArrayList<Integer> ENCHANT_ARMOR_JEWELRY_CRYSTAL = new ArrayList<Integer>();
	public static ArrayList<Integer> ENCHANT_ARMOR_JEWELRY_BLESSED = new ArrayList<Integer>();
	public static ArrayList<Integer> ENCHANT_OLF_TSHIRT_CHANCES = new ArrayList<Integer>();
	public static int ENCHANT_MAX;
	public static int ENCHANT_MAX_DIVINE_SCROLL_WEAPON;
	public static int ENCHANT_MAX_DIVINE_SCROLL_ARMOR;
	public static int ENCHANT_MAX_DIVINE_SCROLL_JEWELRY;
	public static int ENCHANT_MAX_WEAPON;
	public static int ENCHANT_MAX_ARMOR;
	public static int ENCHANT_MAX_JEWELRY;
	public static int ENCHANT_MAX_OLF_T_SHIRT;
	public static int ENCHANT_ATTRIBUTE_STONE_CHANCE;
	public static int ENCHANT_ATTRIBUTE_CRYSTAL_CHANCE;
	public static int ARMOR_OVERENCHANT_HPBONUS_LIMIT;
	public static boolean SHOW_ENCHANT_EFFECT_RESULT;
	//Captcha
	public static boolean CAPTCHA_ALLOW;
	public static long CAPTCHA_ANSWER_SECONDS;
	public static long CAPTCHA_JAIL_SECONDS;
	public static int CAPTCHA_COUNT;
	public static String[] CAPTCHA_PUNISHMENT;
	public static boolean REGEN_SIT_WAIT;
	public static double RATE_RAID_REGEN;
	public static double RATE_RAID_DEFENSE;
	public static double RATE_RAID_ATTACK;
	public static double RATE_EPIC_DEFENSE;
	public static double RATE_EPIC_ATTACK;
	public static int RAID_MAX_LEVEL_DIFF;
	public static boolean PARALIZE_ON_RAID_DIFF;
	public static int MUTATED_ELPY_COUNT;
	public static boolean FRINTEZZA_ALL_MEMBERS_NEED_SCROLL;
	public static double ALT_PK_DEATH_RATE;
	public static int STARTING_ADENA;
	public static int STARTING_LVL;
	public static boolean HTML_WELCOME;
	public static boolean ENTER_WORLD_ANNOUNCEMENTS_HERO_LOGIN;
	public static boolean ENTER_WORLD_ANNOUNCEMENTS_LORD_LOGIN;
	//public static boolean ENTER_WORLD_SHOW_HTML_LOCK;
	public static int PREMIUM_ACCOUNT_TYPE;
	public static int PREMIUM_ACCOUNT_PARTY_GIFT_ID;
	public static boolean ENTER_WORLD_SHOW_HTML_PREMIUM_BUY;
	public static boolean ENTER_WORLD_SHOW_HTML_PREMIUM_DONE;
	public static boolean ENTER_WORLD_SHOW_HTML_PREMIUM_ACTIVE;
	/** Deep Blue Mobs' Drop Rules Enabled */
	public static String VOTE_REWARD_MSG;
	public static boolean DEEPBLUE_DROP_RULES;
	public static int DEEPBLUE_DROP_MAXDIFF;
	public static int DEEPBLUE_DROP_RAID_MAXDIFF;
	public static boolean UNSTUCK_SKILL;
	/** telnet enabled */
	public static boolean IS_TELNET_ENABLED;
	public static String TELNET_DEFAULT_ENCODING;
	public static String TELNET_PASSWORD;
	public static String TELNET_HOSTNAME;
	public static int TELNET_PORT;
	/** Percent CP is restore on respawn */
	public static double RESPAWN_RESTORE_CP;
	/** Percent HP is restore on respawn */
	public static double RESPAWN_RESTORE_HP;
	/** Percent MP is restore on respawn */
	public static double RESPAWN_RESTORE_MP;
	/** Maximum number of available slots for pvt stores (sell/buy) - Dwarves */
	public static int MAX_PVTSTORE_SLOTS_DWARF;
	/** Maximum number of available slots for pvt stores (sell/buy) - Others */
	public static int MAX_PVTSTORE_SLOTS_OTHER;
	public static int MAX_PVTCRAFT_SLOTS;
	public static boolean SENDSTATUS_TRADE_JUST_OFFLINE;
	public static double SENDSTATUS_TRADE_MOD;
	public static boolean SHOW_OFFLINE_MODE_IN_ONLINE;
	public static boolean ALLOW_CH_DOOR_OPEN_ON_CLICK;
	public static boolean ALT_CH_ALL_BUFFS;
	public static boolean ALT_CH_ALLOW_1H_BUFFS;
	public static boolean ALT_CH_SIMPLE_DIALOG;
	public static int CH_BID_GRADE1_MINCLANLEVEL;
	public static int CH_BID_GRADE1_MINCLANMEMBERS;
	public static int CH_BID_GRADE1_MINCLANMEMBERSLEVEL;
	public static int CH_BID_GRADE2_MINCLANLEVEL;
	public static int CH_BID_GRADE2_MINCLANMEMBERS;
	public static int CH_BID_GRADE2_MINCLANMEMBERSLEVEL;
	public static int CH_BID_GRADE3_MINCLANLEVEL;
	public static int CH_BID_GRADE3_MINCLANMEMBERS;
	public static int CH_BID_GRADE3_MINCLANMEMBERSLEVEL;
	public static double RESIDENCE_LEASE_FUNC_MULTIPLIER;
	public static double RESIDENCE_LEASE_MULTIPLIER;
	// Fame Reward
	public static boolean ENABLE_ALT_FAME_REWARD;
	public static long ALT_FAME_CASTLE;
	public static long ALT_FAME_FORTRESS;
	public static int INTERVAL_FLAG_DROP;
	// Synerge
	public static int SIEGE_WINNER_REPUTATION_REWARD;
	public static boolean ALLOW_START_FORTRESS_SIEGE_FEE;
	public static int START_FORTRESS_SIEGE_PRICE_ID;
	public static int START_FORTRESS_SIEGE_PRICE_AMOUNT;
	public static boolean FORTRESS_SIEGE_ALLOW_SINGLE_PLAYERS;
	public static boolean SIEGE_ALLOW_FAME_WHILE_DEAD;
	public static boolean SIEGE_ALLOW_FAME_IN_SAFE;
	public static int[][] SIEGE_REWARDS_NEAR_FAME = null;
	public static boolean FORTRESS_REMOVE_FLAG_ON_LEAVE_ZONE;
	public static boolean DOMINION_REMOVE_FLAG_ON_LEAVE_ZONE;
	public static boolean ACCEPT_ALTERNATE_ID;
	public static int REQUEST_ID;
	public static boolean ANNOUNCE_MAMMON_SPAWN;
	public static int GM_NAME_COLOUR;
	public static boolean GM_HERO_AURA;
	public static int NORMAL_NAME_COLOUR;
	public static int CLANLEADER_NAME_COLOUR;
	/** AI */
	public static boolean ALLOW_NPC_AIS;
	public static int AI_TASK_MANAGER_COUNT;
	public static long AI_TASK_ATTACK_DELAY;
	public static long AI_TASK_ACTIVE_DELAY;
	public static boolean BLOCK_ACTIVE_TASKS;
	public static boolean ALWAYS_TELEPORT_HOME;
	public static boolean RND_WALK;
	public static int RND_WALK_RATE;
	public static int RND_ANIMATION_RATE;
	public static int AGGRO_CHECK_INTERVAL;
	public static long NONAGGRO_TIME_ONTELEPORT;
	/** Maximum range mobs can randomly go from spawn point */
	public static int MAX_DRIFT_RANGE;
	/** Maximum range mobs can pursue agressor from spawn point */
	public static int MAX_PURSUE_RANGE;
	public static int MAX_PURSUE_UNDERGROUND_RANGE;
	public static int MAX_PURSUE_RANGE_RAID;
	public static boolean ALT_DEATH_PENALTY;
	public static boolean ALLOW_DEATH_PENALTY_C5;
	public static int ALT_DEATH_PENALTY_C5_CHANCE;
	public static boolean ALT_DEATH_PENALTY_C5_CHAOTIC_RECOVERY;
	public static int ALT_DEATH_PENALTY_C5_EXPERIENCE_PENALTY;
	public static int ALT_DEATH_PENALTY_C5_KARMA_PENALTY;
	public static boolean HIDE_GM_STATUS;
	public static boolean SHOW_GM_LOGIN;
	public static boolean SAVE_GM_EFFECTS; // Silence, gmspeed, etc...
	public static boolean AUTO_LEARN_SKILLS;
	public static boolean AUTO_LEARN_FORGOTTEN_SKILLS;
	public static int MOVE_PACKET_DELAY;
	public static int ATTACK_PACKET_DELAY;
	public static boolean DAMAGE_FROM_FALLING;
	/** Community Board */
	public static boolean COMMUNITYBOARD_ENABLED;
	public static boolean CUSTOM_X_CB;
	public static String BBS_DEFAULT;
	public static String BBS_HOME_DIR;
	/** Wedding Options */
	public static boolean ALLOW_WEDDING;
	public static int WEDDING_PRICE;
	public static boolean WEDDING_PUNISH_INFIDELITY;
	public static boolean WEDDING_TELEPORT;
	public static int WEDDING_TELEPORT_PRICE;
	public static int WEDDING_TELEPORT_INTERVAL;
	public static boolean WEDDING_SAMESEX;
	public static boolean WEDDING_FORMALWEAR;
	public static int WEDDING_DIVORCE_COSTS;
	/** Augmentations **/
	public static int AUGMENTATION_NG_SKILL_CHANCE; // Chance to get a skill while using a NoGrade Life Stone
	public static int AUGMENTATION_NG_GLOW_CHANCE; // Chance to get a Glow effect while using a NoGrade Life Stone(only if you get a skill)
	public static int AUGMENTATION_MID_SKILL_CHANCE; // Chance to get a skill while using a MidGrade Life Stone
	public static int AUGMENTATION_MID_GLOW_CHANCE; // Chance to get a Glow effect while using a MidGrade Life Stone(only if you get a skill)
	public static int AUGMENTATION_HIGH_SKILL_CHANCE; // Chance to get a skill while using a HighGrade Life Stone
	public static int AUGMENTATION_HIGH_GLOW_CHANCE; // Chance to get a Glow effect while using a HighGrade Life Stone
	public static int AUGMENTATION_TOP_SKILL_CHANCE; // Chance to get a skill while using a TopGrade Life Stone
	public static int AUGMENTATION_TOP_GLOW_CHANCE; // Chance to get a Glow effect while using a TopGrade Life Stone
	public static int AUGMENTATION_BASESTAT_CHANCE; // Chance to get a BaseStatModifier in the augmentation process
	public static int AUGMENTATION_ACC_SKILL_CHANCE;
	public static int FOLLOW_RANGE;
	public static boolean ALT_ENABLE_MULTI_PROFA;
	public static boolean ALT_ITEM_AUCTION_ENABLED;
	public static boolean ALT_ITEM_AUCTION_CAN_REBID;
	public static boolean ALT_ITEM_AUCTION_START_ANNOUNCE;
	public static int ALT_ITEM_AUCTION_BID_ITEM_ID;
	public static long ALT_ITEM_AUCTION_MAX_BID;
	public static int ALT_ITEM_AUCTION_MAX_CANCEL_TIME_IN_MILLIS;
	public static boolean ALT_FISH_CHAMPIONSHIP_ENABLED;
	public static int ALT_FISH_CHAMPIONSHIP_REWARD_ITEM;
	public static int ALT_FISH_CHAMPIONSHIP_REWARD_1;
	public static int ALT_FISH_CHAMPIONSHIP_REWARD_2;
	public static int ALT_FISH_CHAMPIONSHIP_REWARD_3;
	public static int ALT_FISH_CHAMPIONSHIP_REWARD_4;
	public static int ALT_FISH_CHAMPIONSHIP_REWARD_5;
	public static boolean ALT_ENABLE_BLOCK_CHECKER_EVENT;
	public static int ALT_MIN_BLOCK_CHECKER_TEAM_MEMBERS;
	public static double ALT_RATE_COINS_REWARD_BLOCK_CHECKER;
	public static boolean ALT_HBCE_FAIR_PLAY;
	public static int ALT_PET_INVENTORY_LIMIT;
	public static int ALT_CLAN_LEVEL_CREATE;
	/** limits of stats **/
	public static int LIM_PATK;
	public static int LIM_MATK;
	public static int LIM_PDEF;
	public static int LIM_MDEF;
	public static int LIM_MATK_SPD;
	public static int LIM_PATK_SPD;
	public static int LIM_CRIT_DAM;
	public static int LIM_CRIT;
	public static int LIM_MCRIT;
	public static int LIM_ACCURACY;
	public static int LIM_EVASION;
	public static int LIM_MOVE;
	public static int GM_LIM_MOVE;
	public static int LIM_FAME;
	public static double ALT_NPC_PATK_MODIFIER;
	public static double ALT_NPC_MATK_MODIFIER;
	public static double ALT_NPC_MAXHP_MODIFIER;
	public static double ALT_NPC_MAXMP_MODIFIER;
	public static double ALT_NPC_PDEF_MODIFIER;
	public static double ALT_NPC_MDEF_MODIFIER;
	/** Enchant Config **/
	public static int SAFE_ENCHANT_COMMON;
	public static int SAFE_ENCHANT_FULL_BODY;
	public static int SAFE_ENCHANT_LVL;
	public static int FESTIVAL_MIN_PARTY_SIZE;
	public static double FESTIVAL_RATE_PRICE;
	public static boolean ENABLE_POLL_SYSTEM;
	public static int ANNOUNCE_POLL_EVERY_X_MIN;
	/** DimensionalRift Config **/
	public static int RIFT_MIN_PARTY_SIZE;
	public static int RIFT_SPAWN_DELAY; // Time in ms the party has to wait until the mobs spawn
	public static int RIFT_MAX_JUMPS;
	public static int RIFT_AUTO_JUMPS_TIME;
	public static int RIFT_AUTO_JUMPS_TIME_RAND;
	public static int RIFT_ENTER_COST_RECRUIT;
	public static int RIFT_ENTER_COST_SOLDIER;
	public static int RIFT_ENTER_COST_OFFICER;
	public static int RIFT_ENTER_COST_CAPTAIN;
	public static int RIFT_ENTER_COST_COMMANDER;
	public static int RIFT_ENTER_COST_HERO;
	public static boolean ALLOW_TALK_WHILE_SITTING;
	public static boolean PARTY_LEADER_ONLY_CAN_INVITE;
	public static boolean ALLOW_CLANSKILLS;
	public static boolean ALLOW_LEARN_TRANS_SKILLS_WO_QUEST;
	/** Allow Manor system */
	public static boolean ALLOW_MANOR;
	/** Manor Refresh Starting time */
	public static int MANOR_REFRESH_TIME;
	/** Manor Refresh Min */
	public static int MANOR_REFRESH_MIN;
	/** Manor Next Period Approve Starting time */
	public static int MANOR_APPROVE_TIME;
	/** Manor Next Period Approve Min */
	public static int MANOR_APPROVE_MIN;
	/** Manor Maintenance Time */
	public static int MANOR_MAINTENANCE_PERIOD;
	public static double EVENT_CofferOfShadowsPriceRate;
	public static double EVENT_CofferOfShadowsRewardRate;
	public static double EVENT_APIL_FOOLS_DROP_CHANCE;
	public static boolean EVENT_FLOW_OF_HORROR;
	public static boolean EVENT_APRIL_FOOLS_DAY;
	public static boolean EVENT_CHRISTMAS;
	public static boolean EVENT_COFFER_SHADOWS;
	public static boolean EVENT_FREYA;
	public static boolean EVENT_VITALITY_GIFT;
	public static boolean EVENT_GLIT_MEDAL;
	public static boolean EVENT_HEART;
	public static boolean EVENT_LETTER_COLLECTION;
	public static boolean EVENT_MARCH8;
	public static boolean EVENT_MASTER_ENCHANTING;
	public static boolean EVENT_PC_CAFFE_EXCHANGE;
	public static boolean EVENT_SAVING_SNOWMAN;
	public static boolean EVENT_SUMMER_MELEONS;
	public static boolean EVENT_FALL_HARVEST;
	public static boolean EVENT_TRICK_OF_TRANS;
	/** Master Yogi event enchant config */
	public static int ENCHANT_CHANCE_MASTER_YOGI_STAFF;
	public static int ENCHANT_MAX_MASTER_YOGI_STAFF;
	public static int SAFE_ENCHANT_MASTER_YOGI_STAFF;
	public static boolean AllowCustomDropItems;
	public static int[] CDItemsId;
	public static int[] CDItemsCountDropMin;
	public static int[] CDItemsCountDropMax;
	public static double[] CustomDropItemsChance;
	public static boolean CDItemsAllowMinMaxPlayerLvl;
	public static int CDItemsMinPlayerLvl;
	public static int CDItemsMaxPlayerLvl;
	public static boolean CDItemsAllowMinMaxMobLvl;
	public static int CDItemsMinMobLvl;
	public static int CDItemsMaxMobLvl;
	public static boolean CDItemsAllowOnlyRbDrops;
	public static double EVENT_TFH_POLLEN_CHANCE;
	public static double EVENT_GLITTMEDAL_NORMAL_CHANCE;
	public static double EVENT_GLITTMEDAL_GLIT_CHANCE;
	public static double EVENT_L2DAY_LETTER_CHANCE;
	public static double EVENT_CHANGE_OF_HEART_CHANCE;
	public static double EVENT_TRICK_OF_TRANS_CHANCE;
	public static double EVENT_MARCH8_DROP_CHANCE;
	public static double EVENT_MARCH8_PRICE_RATE;
	public static boolean EVENT_BOUNTY_HUNTERS_ENABLED;
	public static long EVENT_SAVING_SNOWMAN_LOTERY_PRICE;
	public static int EVENT_SAVING_SNOWMAN_REWARDER_CHANCE;
	// RandomBoss Event
	public static boolean RANDOM_BOSS_ENABLE;
	public static int RANDOM_BOSS_ID;
	public static int RANDOM_BOSS_TIME;
	public static int RANDOM_BOSS_X;
	public static int RANDOM_BOSS_Y;
	public static int RANDOM_BOSS_Z;
	//Fight Club
	public static boolean ALLOW_FIGHT_CLUB;
	public static boolean FIGHT_CLUB_HWID_CHECK;
	public static int FIGHT_CLUB_DISALLOW_EVENT;
	public static boolean FIGHT_CLUB_EQUALIZE_ROOMS;
	//Santa Event
	public static boolean EVENT_SANTA_ALLOW;
	public static double EVENT_SANTA_CHANCE_MULT;
	public static boolean SERVICES_NO_TRADE_ONLY_OFFLINE;
	public static boolean SERVICES_NO_TRADE_BLOCK_ZONE;
	public static double SERVICES_TRADE_TAX;
	public static double SERVICES_OFFSHORE_TRADE_TAX;
	public static boolean SERVICES_OFFSHORE_NO_CASTLE_TAX;
	public static boolean SERVICES_TRADE_TAX_ONLY_OFFLINE;
	public static boolean SERVICES_TRADE_ONLY_FAR;
	public static int SERVICES_TRADE_RADIUS;
	public static int SERVICES_TRADE_MIN_LEVEL;
	public static boolean SERVICES_ENABLE_NO_CARRIER;
	public static int SERVICES_NO_CARRIER_DEFAULT_TIME;
	public static int SERVICES_NO_CARRIER_MAX_TIME;
	public static int SERVICES_NO_CARRIER_MIN_TIME;
	public static boolean SERVICES_PK_PVP_KILL_ENABLE;
	public static int SERVICES_PVP_KILL_REWARD_ITEM;
	public static long SERVICES_PVP_KILL_REWARD_COUNT;
	public static int SERVICES_PK_KILL_REWARD_ITEM;
	public static long SERVICES_PK_KILL_REWARD_COUNT;
	public static boolean SERVICES_PK_PVP_TIE_IF_SAME_IP;
	//Announce PK/PvP
	public static boolean SERVICES_ANNOUNCE_PK_ENABLED;
	public static boolean SERVICES_ANNOUNCE_PVP_ENABLED;
	public static boolean ALT_OPEN_CLOAK_SLOT;
	public static boolean ALT_SHOW_SERVER_TIME;
	public static boolean CONSUMABLE_SHOT;
	public static boolean CONSUMABLE_ARROW;
	/** Geodata config */
	public static int GEO_X_FIRST, GEO_Y_FIRST, GEO_X_LAST, GEO_Y_LAST;
	public static String GEOFILES_PATTERN;
	public static boolean ALLOW_GEODATA;
	public static boolean ALLOW_FALL_FROM_WALLS;
	public static boolean ALLOW_KEYBOARD_MOVE;
	public static boolean COMPACT_GEO;
	public static int CLIENT_Z_SHIFT;
	public static int MAX_Z_DIFF;
	public static int MIN_LAYER_HEIGHT;
	public static int REGION_EDGE_MAX_Z_DIFF;
	/** Geodata (Pathfind) config */
	public static int PATHFIND_BOOST;
	public static int PATHFIND_MAP_MUL;
	public static boolean PATHFIND_DIAGONAL;
	public static boolean PATH_CLEAN;
	public static int PATHFIND_MAX_Z_DIFF;
	public static long PATHFIND_MAX_TIME;
	public static String PATHFIND_BUFFERS;
	public static int GEODATA_SKILL_CHECK_TASK_INTERVAL;
	public static boolean DEBUG;
	/* Item-Mall Configs */
	public static int GAME_POINT_ITEM_ID;
	public static int WEAR_DELAY;
	public static boolean GOODS_INVENTORY_ENABLED = false;
	public static boolean EX_NEW_PETITION_SYSTEM;
	public static boolean EX_JAPAN_MINIGAME;
	public static boolean EX_LECTURE_MARK;
	public static boolean AUTH_SERVER_GM_ONLY;
	public static boolean AUTH_SERVER_BRACKETS;
	public static boolean AUTH_SERVER_IS_PVP;
	public static int AUTH_SERVER_AGE_LIMIT;
	public static int AUTH_SERVER_SERVER_TYPE;
	/* Version Configs */
	public static String SERVER_VERSION;
	public static String SERVER_BUILD_DATE;
	public static int SERVICES_RATE_TYPE;
	public static int SERVICES_RATE_CREATE_PA;
	public static int[] SERVICES_RATE_BONUS_PRICE;
	public static int[] SERVICES_RATE_BONUS_ITEM;
	public static double[] SERVICES_RATE_BONUS_VALUE;
	public static int[] SERVICES_RATE_BONUS_DAYS;
	public static int ENCHANT_CHANCE_WEAPON_PA;
	public static int ENCHANT_CHANCE_ARMOR_PA;
	public static int ENCHANT_CHANCE_ACCESSORY_PA;
	public static int ENCHANT_CHANCE_WEAPON_BLESS_PA;
	public static int ENCHANT_CHANCE_ARMOR_BLESS_PA;
	public static int ENCHANT_CHANCE_ACCESSORY_BLESS_PA;
	public static int ENCHANT_CHANCE_CRYSTAL_WEAPON_PA;
	public static int ENCHANT_CHANCE_CRYSTAL_ARMOR_PA;
	public static int ENCHANT_CHANCE_CRYSTAL_ACCESSORY_PA;
	public static double SERVICES_BONUS_XP;
	public static double SERVICES_BONUS_SP;
	public static double SERVICES_BONUS_ADENA;
	public static double SERVICES_BONUS_ITEMS;
	public static double SERVICES_BONUS_SPOIL;
	/* Password changer */
	public static boolean SERVICES_CHANGE_PASSWORD;
	public static int PASSWORD_PAY_ID;
	public static long PASSWORD_PAY_COUNT;
	public static String APASSWD_TEMPLATE;
	/* Fake List */
	public static boolean ALLOW_FAKE_PLAYERS;
	public static boolean FAKE_PLAYERS_SIT;
	public static int FAKE_PLAYERS_PERCENT;
	public static boolean ALLOW_ONLINE_PARSE;
	public static int FIRST_UPDATE;
	public static int DELAY_UPDATE;
	// Bot Report
	public static boolean ENABLE_AUTO_HUNTING_REPORT;

	public static long MAX_PLAYER_CONTRIBUTION;
	public static boolean AUTO_LOOT_PA;
	/* Epics */
	public static int ANTHARAS_DEFAULT_SPAWN_HOURS;
	public static int ANTHARAS_RANDOM_SPAWN_HOURS;
	public static int VALAKAS_DEFAULT_SPAWN_HOURS;
	public static int VALAKAS_RANDOM_SPAWN_HOURS;
	public static int BAIUM_DEFAULT_SPAWN_HOURS;
	public static int BAIUM_RANDOM_SPAWN_HOURS;
	public static int FIXINTERVALOFBAYLORSPAWN_HOUR;
	public static int RANDOMINTERVALOFBAYLORSPAWN;
	public static int FIXINTERVALOFBELETHSPAWN_HOUR;
	public static int BELETH_CLONES_RESPAWN_TIME;
	public static int FIXINTERVALOFSAILRENSPAWN_HOUR;
	public static int RANDOMINTERVALOFSAILRENSPAWN;
	public static int MIN_PLAYERS_TO_SPAWN_BELETH;
	public static int CLAN_LEVEL_6_COST;
	public static int CLAN_LEVEL_7_COST;
	public static int CLAN_LEVEL_8_COST;
	public static int CLAN_LEVEL_9_COST;
	public static int CLAN_LEVEL_10_COST;
	public static int CLAN_LEVEL_11_COST;
	public static int CLAN_LEVEL_6_REQUIREMEN;
	public static int CLAN_LEVEL_7_REQUIREMEN;
	public static int CLAN_LEVEL_8_REQUIREMEN;
	public static int CLAN_LEVEL_9_REQUIREMEN;
	public static int CLAN_LEVEL_10_REQUIREMEN;
	public static int CLAN_LEVEL_11_REQUIREMEN;
	public static int BLOOD_OATHS;
	public static int BLOOD_PLEDGES;
	public static int MIN_ACADEM_POINT;
	public static int MAX_ACADEM_POINT;
	public static boolean ZONE_PVP_COUNT;
	public static boolean SIEGE_PVP_COUNT;
	public static boolean EPIC_EXPERTISE_PENALTY;
	public static boolean EXPERTISE_PENALTY;
	// Remove dance and songs shot click
	public static boolean ALT_DISPEL_MUSIC;
	public static int ALT_MUSIC_LIMIT;
	public static int ALT_DEBUFF_LIMIT;
	public static int ALT_TRIGGER_LIMIT;
	public static boolean ENABLE_MODIFY_SKILL_DURATION;
	public static boolean ALT_TIME_MODE_SKILL_DURATION;
	public static TIntIntHashMap SKILL_DURATION_LIST;
	public static double ALT_VITALITY_NEVIT_UP_POINT;
	public static double ALT_VITALITY_NEVIT_POINT;
	public static boolean SERVICES_LVL_ENABLED;
	public static int SERVICES_LVL_UP_MAX;
	public static int SERVICES_LVL_UP_PRICE;
	public static int SERVICES_LVL_UP_ITEM;
	public static int SERVICES_LVL_DOWN_MAX;
	public static int SERVICES_LVL_DOWN_PRICE;
	public static int SERVICES_LVL_DOWN_ITEM;
	public static boolean ALLOW_INSTANCES_LEVEL_MANUAL;
	public static boolean ALLOW_INSTANCES_PARTY_MANUAL;
	public static int INSTANCES_LEVEL_MIN;
	public static int INSTANCES_LEVEL_MAX;
	public static int INSTANCES_PARTY_MIN;
	public static int INSTANCES_PARTY_MAX;
	// Items setting
	public static boolean CAN_BE_TRADED_NO_TARADEABLE;
	public static boolean CAN_BE_TRADED_NO_SELLABLE;
	public static boolean CAN_BE_TRADED_NO_STOREABLE;
	public static boolean CAN_BE_TRADED_SHADOW_ITEM;
	public static boolean CAN_BE_TRADED_HERO_WEAPON;
	public static boolean CAN_BE_WH_NO_TARADEABLE;
	public static boolean CAN_BE_CWH_NO_TARADEABLE;
	public static boolean CAN_BE_CWH_IS_AUGMENTED;
	public static boolean CAN_BE_WH_IS_AUGMENTED;
	public static boolean ALLOW_SOUL_SPIRIT_SHOT_INFINITELY;
	public static boolean ALLOW_ARROW_INFINITELY;
	public static boolean ALLOW_START_ITEMS;
	public static int[] START_ITEMS_MAGE;
	public static int[] START_ITEMS_MAGE_COUNT;
	public static int[] START_ITEMS_FITHER;
	public static int[] START_ITEMS_FITHER_COUNT;
	public static int HELLBOUND_LEVEL;
	public static int CLAN_LEAVE_PENALTY;
	public static int ALLY_LEAVE_PENALTY;
	public static int DISSOLVED_ALLY_PENALTY;
	public static int DISSOLVED_CLAN_PENALTY;
	public static int CLAN_DISBAND_TIME;
	public static boolean USE_ALT_ENCHANT_PA;
	public static ArrayList<Integer> ENCHANT_WEAPON_FIGHT_PA = new ArrayList<Integer>();
	public static ArrayList<Integer> ENCHANT_WEAPON_FIGHT_BLESSED_PA = new ArrayList<Integer>();
	public static ArrayList<Integer> ENCHANT_WEAPON_FIGHT_CRYSTAL_PA = new ArrayList<Integer>();
	public static ArrayList<Integer> ENCHANT_ARMOR_PA = new ArrayList<Integer>();
	public static ArrayList<Integer> ENCHANT_ARMOR_CRYSTAL_PA = new ArrayList<Integer>();
	public static ArrayList<Integer> ENCHANT_ARMOR_BLESSED_PA = new ArrayList<Integer>();
	public static ArrayList<Integer> ENCHANT_ARMOR_JEWELRY_PA = new ArrayList<Integer>();
	public static ArrayList<Integer> ENCHANT_ARMOR_JEWELRY_CRYSTAL_PA = new ArrayList<Integer>();
	public static ArrayList<Integer> ENCHANT_ARMOR_JEWELRY_BLESSED_PA = new ArrayList<Integer>();
	public static boolean ALLOW_MULTILANG_GATEKEEPER;
	public static boolean LOAD_CUSTOM_SPAWN;
	public static boolean SAVE_GM_SPAWN;
	// Log items
	public static boolean ENABLE_PLAYER_ITEM_LOGS;
	public static boolean PLAYER_ITEM_LOGS_SAVED_IN_DB;
	public static long PLAYER_ITEM_LOGS_MAX_TIME;
	public static boolean DEBUFF_PROTECTION_SYSTEM;
	public static int BUFF_RETURN_OLYMPIAD_TIME;
	public static int BUFF_RETURN_AUTO_EVENTS_TIME;
	public static int BUFF_RETURN_NORMAL_LOCATIONS_TIME;
	public static String[] FORBIDDEN_CHAR_NAMES;
	public static boolean NORMAL_PLAYER_RECIEVE_MSG_ON_WRONG_ACCOUNT_PASS;
	public static ChatType NORMAL_PLAYER_MSG_TYPE_ON_WRONG_ACCOUNT;
	public static boolean NORMAL_PLAYER_MAIL_ON_WRONG_ACCOUNT_WHILE_OFFLINE;
	public static boolean NORMAL_PLAYER_MAIL_ON_WRONG_SECONDARY_PASSWORD;
	public static int ALT_NEW_CHAR_PREMIUM_ID;
	public static boolean ENABLE_SPECIAL_TUTORIAL;
	public static boolean ENABLE_EMOTIONS;
	public static boolean ALLOW_SKILL_ENCHANTING_OUTSIDE_PEACE_ZONE;
	// Retail macro use bug
	public static boolean ALLOW_MACROS_REUSE_BUG;
	public static boolean ALLOW_MACROS_ENCHANT_BUG;
	// Phantom players
	public static boolean PHANTOM_PLAYERS_ENABLED;
	public static String PHANTOM_PLAYERS_ACCOUNT;
	public static int PHANTOM_MAX_PLAYERS;
	public static int[] PHANTOM_BANNED_CLASSID;
	public static int[] PHANTOM_BANNED_SETID;
	public static int PHANTOM_MAX_WEAPON_GRADE;
	public static int PHANTOM_MAX_ARMOR_GRADE;
	public static int PHANTOM_MAX_JEWEL_GRADE;
	public static int PHANTOM_SPAWN_MAX;
	public static int PHANTOM_SPAWN_DELAY;
	public static int PHANTOM_MAX_LIFETIME;
	public static int CHANCE_TO_ENCHANT_WEAP;
	public static int MAX_ENCH_PHANTOM_WEAP;
	public static int PHANTOM_MAX_DRIFT_RANGE;
	public static boolean ALLOW_PHANTOM_CUSTOM_TITLES;
	public static int PHANTOM_CHANCE_SET_NOBLE_TITLE;
	public static boolean DISABLE_PHANTOM_ACTIONS;
	public static int[] PHANTOM_ALLOWED_NPC_TO_WALK;
	public static int PHANTOM_ROAMING_MAX_WH_CHECKS;
	public static int PHANTOM_ROAMING_MAX_WH_CHECKS_DWARF;
	public static int PHANTOM_ROAMING_MAX_SHOP_CHECKS;
	public static int PHANTOM_ROAMING_MAX_SHOP_CHECKS_DWARF;
	public static int PHANTOM_ROAMING_MAX_NPC_CHECKS;
	public static int PHANTOM_ROAMING_MIN_WH_DELAY;
	public static int PHANTOM_ROAMING_MAX_WH_DELAY;
	public static int PHANTOM_ROAMING_MIN_SHOP_DELAY;
	public static int PHANTOM_ROAMING_MAX_SHOP_DELAY;
	public static int PHANTOM_ROAMING_MIN_NPC_DELAY;
	public static int PHANTOM_ROAMING_MAX_NPC_DELAY;
	public static int PHANTOM_ROAMING_MIN_PRIVATESTORE_DELAY;
	public static int PHANTOM_ROAMING_MAX_PRIVATESTORE_DELAY;
	public static int PHANTOM_ROAMING_MIN_FREEROAM_DELAY;
	public static int PHANTOM_ROAMING_MAX_FREEROAM_DELAY;
	public static boolean DISABLE_PHANTOM_RESPAWN;
	public static boolean DEBUG_PHANTOMS;
	public static int[] PHANTOM_CLANS;
	// Away Manager
	public static boolean ALLOW_AWAY_STATUS;
	public static boolean AWAY_ONLY_FOR_PREMIUM;
	public static int AWAY_TIMER;
	public static int BACK_TIMER;
	public static int AWAY_TITLE_COLOR;
	public static boolean AWAY_PLAYER_TAKE_AGGRO;
	public static boolean AWAY_PEACE_ZONE;
	
	public static void loadServerConfig()
	{
		ExProperties serverSettings = load(CONFIGURATION_FILE);

		LOG_SERVICES = serverSettings.getProperty("Services", false);
		GAME_SERVER_LOGIN_HOST = serverSettings.getProperty("LoginHost", "127.0.0.1");
		GAME_SERVER_LOGIN_PORT = serverSettings.getProperty("LoginPort", 9013);
		GAME_SERVER_LOGIN_CRYPT = serverSettings.getProperty("LoginUseCrypt", true);
		AUTH_SERVER_AGE_LIMIT = serverSettings.getProperty("ServerAgeLimit", 0);
		AUTH_SERVER_GM_ONLY = serverSettings.getProperty("ServerGMOnly", false);
		AUTH_SERVER_BRACKETS = serverSettings.getProperty("ServerBrackets", false);
		AUTH_SERVER_IS_PVP = serverSettings.getProperty("PvPServer", false);
		for (String a : serverSettings.getProperty("ServerType", ArrayUtils.EMPTY_STRING_ARRAY))
		{
			if (a.trim().isEmpty())
			{
				continue;
			}

			ServerType t = ServerType.valueOf(a.toUpperCase());
			AUTH_SERVER_SERVER_TYPE |= t.getMask();
		}
		SECOND_AUTH_ENABLED = serverSettings.getProperty("SAEnabled", false);
		SECOND_AUTH_BAN_ACC = serverSettings.getProperty("SABanAccEnabled", false);
		SECOND_AUTH_STRONG_PASS = serverSettings.getProperty("SAStrongPass", false);
		SECOND_AUTH_MAX_ATTEMPTS = serverSettings.getProperty("SAMaxAttemps", 5);
		SECOND_AUTH_BAN_TIME = serverSettings.getProperty("SABanTime", 480);
		SECOND_AUTH_REC_LINK = serverSettings.getProperty("SARecoveryLink", "http://www.my-domain.com/charPassRec.php");
		INTERNAL_HOSTNAME = serverSettings.getProperty("InternalHostname", "*");
		EXTERNAL_HOSTNAME = serverSettings.getProperty("ExternalHostname", "*");
		ADVIPSYSTEM = serverSettings.getProperty("AdvIPSystem", false);
		useNewProxies = serverSettings.getProperty("useNewProxySystem", true);
		REQUEST_ID = serverSettings.getProperty("RequestServerID", 0);
		ACCEPT_ALTERNATE_ID = serverSettings.getProperty("AcceptAlternateID", true);
		GAMESERVER_HOSTNAME = serverSettings.getProperty("GameserverHostname");
		PORTS_GAME = serverSettings.getProperty("GameserverPort", new int[]
			{
			7777
			});

		SERVER_NAME = serverSettings.getProperty("ServerName", "Server");
		SHORT_SERVER_NAME = serverSettings.getProperty("ShortServerName", "Server");
		SERVER_SUB_NAME = serverSettings.getProperty("ServerSubName", "Server");
		EVERYBODY_HAS_ADMIN_RIGHTS = serverSettings.getProperty("EverybodyHasAdminRights", false);
		HIDE_GM_STATUS = serverSettings.getProperty("HideGMStatus", false);
		SHOW_GM_LOGIN = serverSettings.getProperty("ShowGMLogin", true);
		SAVE_GM_EFFECTS = serverSettings.getProperty("SaveGMEffects", false);
		CHAR_NAME_TEMPLATE = serverSettings.getProperty("CnameTemplate", "([0-9A-Za-z]{2,16})|([0-9\u0410-\u044f-\u4e00-\u9fa5]{2,16})");
		CLAN_NAME_TEMPLATE = serverSettings.getProperty("ClanNameTemplate", "[A-Za-z0-9\u0410-\u042f\u0430-\u044f]{3,16}");
		CLAN_TITLE_TEMPLATE = serverSettings.getProperty("ClanTitleTemplate", "[A-Za-z0-9\u0410-\u042f\u0430-\u044f \\p{Punct}]{1,16}");
		ALLY_NAME_TEMPLATE = serverSettings.getProperty("AllyNameTemplate", "[A-Za-z0-9\u0410-\u042f\u0430-\u044f]{3,16}");
		PARALIZE_ON_RAID_DIFF = serverSettings.getProperty("ParalizeOnRaidLevelDiff", true);
		AUTODESTROY_ITEM_AFTER = serverSettings.getProperty("AutoDestroyDroppedItemAfter", 0);
		AUTODESTROY_PLAYER_ITEM_AFTER = serverSettings.getProperty("AutoDestroyPlayerDroppedItemAfter", 0);
		DELETE_DAYS = serverSettings.getProperty("DeleteCharAfterDays", 7);
		try
		{
			DATAPACK_ROOT = new File(serverSettings.getProperty("DatapackRoot", ".")).getCanonicalFile();
		}
		catch (IOException e)
		{
			_log.error("Error while loading DATAPACK_ROOT", e);
		}
		ALLOW_DISCARDITEM = serverSettings.getProperty("AllowDiscardItem", true);
		ALLOW_DISCARDITEM_AT_PEACE = serverSettings.getProperty("AllowDiscardItemInTown", true);
		ALLOW_MAIL = serverSettings.getProperty("AllowMail", true);
		ALLOW_WAREHOUSE = serverSettings.getProperty("AllowWarehouse", true);
		ALLOW_WATER = serverSettings.getProperty("AllowWater", true);
		ALLOW_CURSED_WEAPONS = serverSettings.getProperty("AllowCursedWeapons", false);
		DROP_CURSED_WEAPONS_ON_KICK = serverSettings.getProperty("DropCursedWeaponsOnKick", false);
		PACKET_FLOOD_PROTECTION_IN_MS = serverSettings.getProperty("PacketFloodProtectionInMs", 50);
		ENTER_WORLD_FLOOD_PROECTION_IN_MS = serverSettings.getProperty("EnterWorldFloodProtectionInMs", 50);
		ALLOW_ENTER_INSTANCE = serverSettings.getProperty("AllowEnterInstance", true);
		ALLOW_PRIVATE_STORES = serverSettings.getProperty("AllowStores", true);
		ALLOW_TALK_TO_NPCS = serverSettings.getProperty("AllowTalkToNpcs", true);
		ALLOW_JUST_MOVING = serverSettings.getProperty("AllowJustMoving", false);
		ALLOW_HWID_ENGINE = serverSettings.getProperty("AllowHWIDEngine", true);
		ALLOW_SKILLS_STATS_LOGGER = serverSettings.getProperty("AllowSkillStatsLogger", true);
		ALLOW_ITEMS_LOGGING = serverSettings.getProperty("AllowItemsLogging", true);
		ALLOW_SPAWN_PROTECTION = serverSettings.getProperty("AllowSpawnProtection", true);
		MIN_PROTOCOL_REVISION = serverSettings.getProperty("MinProtocolRevision", 267);
		MAX_PROTOCOL_REVISION = serverSettings.getProperty("MaxProtocolRevision", 271);
		AUTOSAVE = serverSettings.getProperty("Autosave", true);
		MAXIMUM_ONLINE_USERS = serverSettings.getProperty("MaximumOnlineUsers", 3000);
		ONLINE_PLUS = serverSettings.getProperty("OnlineUsersPlus", 1);
		DATABASE_DRIVER = serverSettings.getProperty("Driver", "com.mysql.jdbc.Driver");
		DATABASE_MAX_CONNECTIONS = serverSettings.getProperty("MaximumDbConnections", 10);
		DATABASE_MAX_IDLE_TIMEOUT = serverSettings.getProperty("MaxIdleConnectionTimeout", 600);
		DATABASE_IDLE_TEST_PERIOD = serverSettings.getProperty("IdleConnectionTestPeriod", 60);
		DATABASE_GAME_URL = serverSettings.getProperty("GameURL", "jdbc:mysql://localhost/l2jdb");
		DATABASE_GAME_USER = serverSettings.getProperty("GameUser", "root");
		DATABASE_GAME_PASSWORD = serverSettings.getProperty("GamePassword", "");
		DATABASE_LOGIN_URL = serverSettings.getProperty("LoginURL", "jdbc:mysql://localhost/l2jdb");
		DATABASE_LOGIN_USER = serverSettings.getProperty("LoginUser", "root");
		DATABASE_LOGIN_PASSWORD = serverSettings.getProperty("LoginPassword", "");
		MYSQL_DUMP_PATH = serverSettings.getProperty("MySqlDumpPath", "");
		USER_INFO_INTERVAL = serverSettings.getProperty("UserInfoInterval", 100L);
		BROADCAST_CHAR_INFO_INTERVAL = serverSettings.getProperty("BroadcastCharInfoInterval", 100L);
		EFFECT_TASK_MANAGER_COUNT = serverSettings.getProperty("EffectTaskManagers", 2);
		SCHEDULED_THREAD_POOL_SIZE = serverSettings.getProperty("ScheduledThreadPoolSize", NCPUS * 4);
		EXECUTOR_THREAD_POOL_SIZE = serverSettings.getProperty("ExecutorThreadPoolSize", NCPUS * 2);
		ENABLE_RUNNABLE_STATS = serverSettings.getProperty("EnableRunnableStats", false);
		SELECTOR_CONFIG.SLEEP_TIME = serverSettings.getProperty("SelectorSleepTime", 10L);
		SELECTOR_CONFIG.INTEREST_DELAY = serverSettings.getProperty("InterestDelay", 30L);
		SELECTOR_CONFIG.MAX_SEND_PER_PASS = serverSettings.getProperty("MaxSendPerPass", 32);
		SELECTOR_CONFIG.READ_BUFFER_SIZE = serverSettings.getProperty("ReadBufferSize", 65536);
		SELECTOR_CONFIG.WRITE_BUFFER_SIZE = serverSettings.getProperty("WriteBufferSize", 131072);
		SELECTOR_CONFIG.HELPER_BUFFER_COUNT = serverSettings.getProperty("BufferPoolSize", 64);
		DEFAULT_LANG = serverSettings.getProperty("DefaultLang", "ru");
		RESTART_AT_TIME = serverSettings.getProperty("AutoRestartAt", "0 5 * * *");
		BACKUP_DURING_AUTO_RESTART = serverSettings.getProperty("BackupDuringAutoRestart", false);
		final String backupDates = serverSettings.getProperty("AdditionalBackupDates", "6,40");
		final String[] datesSplit = backupDates.split(";");
		ADDITIONAL_BACKUP_DATES = new int[datesSplit.length][2];
		for (int d=0; d < datesSplit.length; d++)
		{
			final String[] dates = datesSplit[d].split(",");
			for (int i=0; i < 2; i++)
			{
				ADDITIONAL_BACKUP_DATES[d][0] = Integer.parseInt(dates[0]);
				ADDITIONAL_BACKUP_DATES[d][1] = Integer.parseInt(dates[1]);
			}
		}
		SHIFT_BY = serverSettings.getProperty("HShift", 12);
		SHIFT_BY_Z = serverSettings.getProperty("VShift", 11);
		MAP_MIN_Z = serverSettings.getProperty("MapMinZ", -32768);
		MAP_MAX_Z = serverSettings.getProperty("MapMaxZ", 32767);
		MOVE_PACKET_DELAY = serverSettings.getProperty("MovePacketDelay", 100);
		ATTACK_PACKET_DELAY = serverSettings.getProperty("AttackPacketDelay", 500);
		DAMAGE_FROM_FALLING = serverSettings.getProperty("DamageFromFalling", true);
		MAX_REFLECTIONS_COUNT = serverSettings.getProperty("MaxReflectionsCount", 300);
		WEAR_DELAY = serverSettings.getProperty("WearDelay", 5);
		HTM_CACHE_MODE = serverSettings.getProperty("HtmCacheMode", HtmCache.LAZY);
		ALT_VITALITY_NEVIT_UP_POINT = serverSettings.getProperty("AltVitalityNevitUpPoint", 100);
		ALT_VITALITY_NEVIT_POINT = serverSettings.getProperty("AltVitalityNevitPoint", 100);
		ALLOW_ADDONS_CONFIG = serverSettings.getProperty("AllowAddonsConfig", false);
		ALLOW_IP_LOCK = serverSettings.getProperty("AllowLockIP", false);
		ALLOW_HWID_LOCK = serverSettings.getProperty("AllowLockHwid", false);
		ENABLE_SECONDARY_PASSWORD = serverSettings.getProperty("EnableSecondaryPassword", true);
		MAIL_USER = serverSettings.getProperty("MailUser", "");
		MAIL_PASS = serverSettings.getProperty("MailPass", "");
		MAIL_SUBJECT = serverSettings.getProperty("MailSubject", "");
		MAIL_MESSAGE = serverSettings.getProperty("MailMessage", "");
		NORMAL_PLAYER_RECIEVE_MSG_ON_WRONG_ACCOUNT_PASS = serverSettings.getProperty("NormalPlayerReceiveMsgOnWrongAccountPass", true);
		NORMAL_PLAYER_MSG_TYPE_ON_WRONG_ACCOUNT = ChatType.getTypeFromName(serverSettings.getProperty("NormalPlayerMsgTypeOnWrongAccount", "TELL"));
		NORMAL_PLAYER_MAIL_ON_WRONG_ACCOUNT_WHILE_OFFLINE = serverSettings.getProperty("NormalPlayerMailOnWrongAccountWhileOffline", false);
		NORMAL_PLAYER_MAIL_ON_WRONG_SECONDARY_PASSWORD = serverSettings.getProperty("NormalPlayerMailOnWrongSecondaryPassword", true);
		FORBIDDEN_CHAR_NAMES = serverSettings.getProperty("ForbiddenCharNames", "").split(",");
		
		MAX_HWID_INSTANCES = serverSettings.getProperty("MaxHwIDInstances", 3);
		MAX_HWID_INSTANCES_PRIVILEGED = serverSettings.getProperty("MaxHwIDInstancesPrivileged", 10);
		PRIVILEGED_HWID = new GArray<String>();
		String P_HWID = serverSettings.getProperty("PrivilegedHwIDs", "aaaaaaaaaaaaaabbbbbbbbbddddddddc,ddddddddddddccccccccccccaaaaaaassssssssss");
		String[] P_HWIDS = P_HWID.split(",", -1);
		for (String hwid : P_HWIDS)
		{
			PRIVILEGED_HWID.add(hwid);
		}
		_log.info("Loaded " + PRIVILEGED_HWID.size() + " privileged HwIDs.");
	}
	public static void loadChatConfig()
	{
		ExProperties chatSettings = load(CHAT_FILE);

		GLOBAL_SHOUT = chatSettings.getProperty("GlobalShout", false);
		GLOBAL_TRADE_CHAT = chatSettings.getProperty("GlobalTradeChat", false);
		CHAT_RANGE = chatSettings.getProperty("ChatRange", 1250);
		SHOUT_OFFSET = chatSettings.getProperty("ShoutOffset", 0);
		TRADE_WORDS = new GArray<String>();
		String T_WORLD = chatSettings.getProperty("TradeWords", "trade,sell,selling,buy,exchange,barter,Ä‚ďż˝Ă˘â‚¬â„˘Ä‚ďż˝Ă‚Ë�Ä‚ďż˝Ă‚Ë�,Ä‚ďż˝Ă˘â‚¬â„˘Ä‚ďż˝Ă‚Ë�S,WTB,WTB,WTT,WTS");
		String[] T_WORLDS = T_WORLD.split(",", -1);
		for (String w : T_WORLDS)
		{
			TRADE_WORDS.add(w);
		}
		_log.info("Loaded " + TRADE_WORDS.size() + " trade words.");
		LOG_CHAT = chatSettings.getProperty("LogChat", false);
		CHAT_MESSAGE_MAX_LEN = chatSettings.getProperty("ChatMessageLimit", 1000);
		ABUSEWORD_BANCHAT = chatSettings.getProperty("ABUSEWORD_BANCHAT", false);
		int counter = 0;
		for (int id : chatSettings.getProperty("ABUSEWORD_BAN_CHANNEL", new int[]{ 0 }))
		{
			BAN_CHANNEL_LIST[counter] = id;
			counter++;
		}
		ABUSEWORD_REPLACE = chatSettings.getProperty("ABUSEWORD_REPLACE", false);
		ABUSEWORD_REPLACE_STRING = chatSettings.getProperty("ABUSEWORD_REPLACE_STRING", "[censored]");
		BANCHAT_ANNOUNCE = chatSettings.getProperty("BANCHAT_ANNOUNCE", true);
		BANCHAT_ANNOUNCE_FOR_ALL_WORLD = chatSettings.getProperty("BANCHAT_ANNOUNCE_FOR_ALL_WORLD", true);
		BANCHAT_ANNOUNCE_NICK = chatSettings.getProperty("BANCHAT_ANNOUNCE_NICK", true);
		ABUSEWORD_BANTIME = chatSettings.getProperty("ABUSEWORD_UNBAN_TIMER", 30);
		CHATFILTER_MIN_LEVEL = chatSettings.getProperty("ChatFilterMinLevel", 0);
		CHATS_REQUIRED_LEVEL = chatSettings.getProperty("ChatsRequiredLevel", 21);
		PM_REQUIRED_LEVEL = chatSettings.getProperty("PMPlayersInChat", 61);
		SHOUT_REQUIRED_LEVEL = chatSettings.getProperty("ShoutingInChat", 61);
		RECORD_WROTE_CHAT_MSGS_COUNT = chatSettings.getProperty("RecordWroteChatMsgsCount", false);
		ANNOUNCE_VOTE_DELAY = chatSettings.getProperty("AnnounceVoteDelay", 60);
		counter = 0;
		for (int id : chatSettings.getProperty("ChatFilterChannels", new int[]{ 1, 8 }))
		{
			CHATFILTER_CHANNELS[counter] = id;
			counter++;
		}
		CHATFILTER_WORK_TYPE = chatSettings.getProperty("ChatFilterWorkType", 1);
	}

	public static void loadTelnetConfig()
	{
		ExProperties telnetSettings = load(TELNET_CONFIGURATION_FILE);

		IS_TELNET_ENABLED = telnetSettings.getProperty("EnableTelnet", false);
		TELNET_DEFAULT_ENCODING = telnetSettings.getProperty("TelnetEncoding", "UTF-8");
		TELNET_PORT = telnetSettings.getProperty("Port", 7000);
		TELNET_HOSTNAME = telnetSettings.getProperty("BindAddress", "127.0.0.1");
		TELNET_PASSWORD = telnetSettings.getProperty("Password", "");
	}

	public static boolean SPAWN_WEDDING;

	public static void loadWeddingConfig()
	{
		ExProperties weddingSettings = load(WEDDING_FILE);

		ALLOW_WEDDING = weddingSettings.getProperty("AllowWedding", false);
		WEDDING_PRICE = weddingSettings.getProperty("WeddingPrice", 500000);
		WEDDING_PUNISH_INFIDELITY = weddingSettings.getProperty("WeddingPunishInfidelity", true);
		WEDDING_TELEPORT = weddingSettings.getProperty("WeddingTeleport", true);
		WEDDING_TELEPORT_PRICE = weddingSettings.getProperty("WeddingTeleportPrice", 500000);
		WEDDING_TELEPORT_INTERVAL = weddingSettings.getProperty("WeddingTeleportInterval", 120);
		WEDDING_SAMESEX = weddingSettings.getProperty("WeddingAllowSameSex", true);
		WEDDING_FORMALWEAR = weddingSettings.getProperty("WeddingFormalWear", true);
		WEDDING_DIVORCE_COSTS = weddingSettings.getProperty("WeddingDivorceCosts", 20);
		SPAWN_WEDDING = weddingSettings.getProperty("SPAWN_WEDDING", false);
	}

	public static void loadResidenceConfig()
	{
		ExProperties residenceSettings = load(RESIDENCE_CONFIG_FILE);

		CH_BID_GRADE1_MINCLANLEVEL = residenceSettings.getProperty("ClanHallBid_Grade1_MinClanLevel", 2);
		CH_BID_GRADE1_MINCLANMEMBERS = residenceSettings.getProperty("ClanHallBid_Grade1_MinClanMembers", 1);
		CH_BID_GRADE1_MINCLANMEMBERSLEVEL = residenceSettings.getProperty("ClanHallBid_Grade1_MinClanMembersAvgLevel", 1);
		CH_BID_GRADE2_MINCLANLEVEL = residenceSettings.getProperty("ClanHallBid_Grade2_MinClanLevel", 2);
		CH_BID_GRADE2_MINCLANMEMBERS = residenceSettings.getProperty("ClanHallBid_Grade2_MinClanMembers", 1);
		CH_BID_GRADE2_MINCLANMEMBERSLEVEL = residenceSettings.getProperty("ClanHallBid_Grade2_MinClanMembersAvgLevel", 1);
		CH_BID_GRADE3_MINCLANLEVEL = residenceSettings.getProperty("ClanHallBid_Grade3_MinClanLevel", 2);
		CH_BID_GRADE3_MINCLANMEMBERS = residenceSettings.getProperty("ClanHallBid_Grade3_MinClanMembers", 1);
		CH_BID_GRADE3_MINCLANMEMBERSLEVEL = residenceSettings.getProperty("ClanHallBid_Grade3_MinClanMembersAvgLevel", 1);
		RESIDENCE_LEASE_FUNC_MULTIPLIER = residenceSettings.getProperty("ResidenceLeaseFuncMultiplier", 1.);
		RESIDENCE_LEASE_MULTIPLIER = residenceSettings.getProperty("ResidenceLeaseMultiplier", 1.);
		PERIOD_CASTLE_SIEGE = residenceSettings.getProperty("CastleSiegeIntervalWeeks", 2);
		final int[] tempCastleValidatonTime = residenceSettings.getProperty("CastleValidationDate", new int[]
		{
		    2,
		    4,
		    2003
		});
		CASTLE_VALIDATION_DATE = Calendar.getInstance();
		CASTLE_VALIDATION_DATE.set(Calendar.DAY_OF_MONTH, tempCastleValidatonTime[0]);
		CASTLE_VALIDATION_DATE.set(Calendar.MONTH, tempCastleValidatonTime[1] - 1);
		CASTLE_VALIDATION_DATE.set(Calendar.YEAR, tempCastleValidatonTime[2]);
		CASTLE_VALIDATION_DATE.set(Calendar.HOUR_OF_DAY, 0);
		CASTLE_VALIDATION_DATE.set(Calendar.MINUTE, 0);
		CASTLE_VALIDATION_DATE.set(Calendar.SECOND, 0);
		CASTLE_VALIDATION_DATE.set(Calendar.MILLISECOND, 0);
		RETURN_WARDS_WHEN_TW_STARTS = residenceSettings.getProperty("ReturnWardsWhenTWStarts", false);
		ENABLE_ALT_FAME_REWARD = residenceSettings.getProperty("AltEnableCustomFame", false);
		ALT_FAME_CASTLE = residenceSettings.getProperty("CastleFame", 125);
		ALT_FAME_FORTRESS = residenceSettings.getProperty("FortressFame", 31);
		FORTRESS_REMOVE_FLAG_ON_LEAVE_ZONE = residenceSettings.getProperty("FortFlagReturnOnLeaveZone", false);
		DOMINION_REMOVE_FLAG_ON_LEAVE_ZONE = residenceSettings.getProperty("ReturnFlagOnSiegeZoneLeave", false);
		INTERVAL_FLAG_DROP = residenceSettings.getProperty("IntervalFlagDrop", 60);
		SIEGE_WINNER_REPUTATION_REWARD = residenceSettings.getProperty("SiegeWinnerReputationReward", 0);
		ALLOW_START_FORTRESS_SIEGE_FEE = residenceSettings.getProperty("AllowStartFortressSiegeFee", true);
		START_FORTRESS_SIEGE_PRICE_ID = residenceSettings.getProperty("StartFortressSiegePriceId", 57);
		START_FORTRESS_SIEGE_PRICE_AMOUNT = residenceSettings.getProperty("StartFortressSiegePriceAmount", 250000);
		FORTRESS_SIEGE_ALLOW_SINGLE_PLAYERS = residenceSettings.getProperty("FortressSiegeAllowSinglePlayers", false);
		SIEGE_ALLOW_FAME_WHILE_DEAD = residenceSettings.getProperty("SiegeAllowFameWhileDead", false);
		SIEGE_ALLOW_FAME_IN_SAFE = residenceSettings.getProperty("SiegeAllowFameInSafe", false);
		final String rewards = residenceSettings.getProperty("SiegeRewardsNearFame", "");
		if (!rewards.isEmpty())
		{
			final String[] rews = rewards.split(";");
			int i = 0;
			SIEGE_REWARDS_NEAR_FAME = new int[rews.length][2];
			for (String reward : rews)
			{
				final String[] parts = reward.split(",");
				SIEGE_REWARDS_NEAR_FAME[i][0] = Integer.parseInt(parts[0]);
				SIEGE_REWARDS_NEAR_FAME[i][1] = Integer.parseInt(parts[1]);
				i++;
			}
		}
	}

	public static void loadItemsUseConfig()
	{
		ExProperties itemsUseSettings = load(ITEM_USE_FILE);

		ITEM_USE_LIST_ID = itemsUseSettings.getProperty("ItemUseListId", new int[]
			{
			725,
			726,
			727,
			728
			});
		ITEM_USE_IS_COMBAT_FLAG = itemsUseSettings.getProperty("ItemUseIsCombatFlag", true);
		ITEM_USE_IS_ATTACK = itemsUseSettings.getProperty("ItemUseIsAttack", true);
		ITEM_USE_IS_EVENTS = itemsUseSettings.getProperty("ItemUseIsEvents", true);
		MAX_ADENA = itemsUseSettings.getProperty("MaxAdena", -1);
	}

	public static void loadSchemeBuffer()
	{
		ExProperties npcbuffer = load(NPCBUFFER_CONFIG_FILE);

		NpcBuffer_VIP = npcbuffer.getProperty("EnableVIP", false);
		NpcBuffer_VIP_ALV = npcbuffer.getProperty("VipAccesLevel", 1);
		NpcBuffer_EnableBuff = npcbuffer.getProperty("EnableBuffSection", true);
		NpcBuffer_EnableScheme = npcbuffer.getProperty("EnableScheme", true);
		NpcBuffer_EnableHeal = npcbuffer.getProperty("EnableHeal", true);
		NpcBuffer_EnableBuffs = npcbuffer.getProperty("EnableBuffs", true);
		NpcBuffer_EnableResist = npcbuffer.getProperty("EnableResist", true);
		NpcBuffer_EnableSong = npcbuffer.getProperty("EnableSongs", true);
		NpcBuffer_EnableDance = npcbuffer.getProperty("EnableDances", true);
		NpcBuffer_EnableChant = npcbuffer.getProperty("EnableChants", true);
		NpcBuffer_EnableOther = npcbuffer.getProperty("EnableOther", true);
		NpcBuffer_EnableSpecial = npcbuffer.getProperty("EnableSpecial", true);
		NpcBuffer_EnableCubic = npcbuffer.getProperty("EnableCubic", false);
		NpcBuffer_EnableCancel = npcbuffer.getProperty("EnableRemoveBuffs", true);
		NpcBuffer_EnableBuffSet = npcbuffer.getProperty("EnableBuffSet", true);
		NpcBuffer_EnableBuffPK = npcbuffer.getProperty("EnableBuffForPK", false);
		NpcBuffer_EnableFreeBuffs = npcbuffer.getProperty("EnableFreeBuffs", true);
		NpcBuffer_EnableTimeOut = npcbuffer.getProperty("EnableTimeOut", true);
		SCHEME_ALLOW_FLAG = npcbuffer.getProperty("EnableBuffforFlag", false);
		NpcBuffer_TimeOutTime = npcbuffer.getProperty("TimeoutTime", 10);
		NpcBuffer_MinLevel = npcbuffer.getProperty("MinimumLevel", 20);
		NpcBuffer_PriceCancel = npcbuffer.getProperty("RemoveBuffsPrice", 100000);
		NpcBuffer_PriceHeal = npcbuffer.getProperty("HealPrice", 100000);
		NpcBuffer_PriceBuffs = npcbuffer.getProperty("BuffsPrice", 100000);
		NpcBuffer_PriceResist = npcbuffer.getProperty("ResistPrice", 100000);
		NpcBuffer_PriceSong = npcbuffer.getProperty("SongPrice", 100000);
		NpcBuffer_PriceDance = npcbuffer.getProperty("DancePrice", 100000);
		NpcBuffer_PriceChant = npcbuffer.getProperty("ChantsPrice", 100000);
		NpcBuffer_PriceOther = npcbuffer.getProperty("OtherPrice", 100000);
		NpcBuffer_PriceSpecial = npcbuffer.getProperty("SpecialPrice", 100000);
		NpcBuffer_PriceCubic = npcbuffer.getProperty("CubicPrice", 100000);
		NpcBuffer_PriceSet = npcbuffer.getProperty("SetPrice", 100000);
		NpcBuffer_PriceScheme = npcbuffer.getProperty("SchemePrice", 100000);
		NpcBuffer_MaxScheme = npcbuffer.getProperty("MaxScheme", 4);
		NpcBuffer_EnablePremiumBuffs = npcbuffer.getProperty("EnablePremiumBuffs", false);
		String[] parts;
		String[] skills = npcbuffer.getProperty("BuffSetMage", "192,1").split(";");
		for (String sk : skills)
		{
			parts = sk.split(",");
			NpcBuffer_BuffSetMage.add(new int[] { Integer.parseInt(parts[0]), Integer.parseInt(parts[1]) });
		}

		skills = npcbuffer.getProperty("BuffSetFighter", "192,1").split(";");
		for (String sk : skills)
		{
			parts = sk.split(",");
			NpcBuffer_BuffSetFighter.add(new int[] { Integer.parseInt(parts[0]), Integer.parseInt(parts[1]) });
		}

		skills = npcbuffer.getProperty("BuffSetDagger", "192,1").split(";");
		for (String sk : skills)
		{
			parts = sk.split(",");
			NpcBuffer_BuffSetDagger.add(new int[] { Integer.parseInt(parts[0]), Integer.parseInt(parts[1]) });
		}

		skills = npcbuffer.getProperty("BuffSetSupport", "192,1").split(";");
		for (String sk : skills)
		{
			parts = sk.split(",");
			NpcBuffer_BuffSetSupport.add(new int[] { Integer.parseInt(parts[0]), Integer.parseInt(parts[1]) });
		}

		skills = npcbuffer.getProperty("BuffSetTank", "192,1").split(";");
		for (String sk : skills)
		{
			parts = sk.split(",");
			NpcBuffer_BuffSetTank.add(new int[] { Integer.parseInt(parts[0]), Integer.parseInt(parts[1]) });
		}

		skills = npcbuffer.getProperty("BuffSetArcher", "192,1").split(";");
		for (String sk : skills)
		{
			parts = sk.split(",");
			NpcBuffer_BuffSetArcher.add(new int[] { Integer.parseInt(parts[0]), Integer.parseInt(parts[1]) });
		}
	}

	public static void loadFightClubSettings()
	{
		ExProperties eventFightClubSettings = load(EVENT_FIGHT_CLUB_FILE);

		FIGHT_CLUB_ENABLED = eventFightClubSettings.getProperty("FightClubEnabled", false);
		MINIMUM_LEVEL_TO_PARRICIPATION = eventFightClubSettings.getProperty("MinimumLevel", 1);
		MAXIMUM_LEVEL_TO_PARRICIPATION = eventFightClubSettings.getProperty("MaximumLevel", 85);
		MAXIMUM_LEVEL_DIFFERENCE = eventFightClubSettings.getProperty("MaximumLevelDifference", 10);
		ALLOWED_RATE_ITEMS = eventFightClubSettings.getProperty("AllowedItems", "").trim().replaceAll(" ", "").split(",");
		PLAYERS_PER_PAGE = eventFightClubSettings.getProperty("RatesOnPage", 10);
		ARENA_TELEPORT_DELAY = eventFightClubSettings.getProperty("ArenaTeleportDelay", 5);
		CANCEL_BUFF_BEFORE_FIGHT = eventFightClubSettings.getProperty("CancelBuffs", true);
		UNSUMMON_PETS = eventFightClubSettings.getProperty("UnsummonPets", true);
		UNSUMMON_SUMMONS = eventFightClubSettings.getProperty("UnsummonSummons", true);
		REMOVE_CLAN_SKILLS = eventFightClubSettings.getProperty("RemoveClanSkills", false);
		REMOVE_HERO_SKILLS = eventFightClubSettings.getProperty("RemoveHeroSkills", false);
		TIME_TO_PREPARATION = eventFightClubSettings.getProperty("TimeToPreparation", 10);
		FIGHT_TIME = eventFightClubSettings.getProperty("TimeToDraw", 300);
		ALLOW_DRAW = eventFightClubSettings.getProperty("AllowDraw", true);
		TIME_TELEPORT_BACK = eventFightClubSettings.getProperty("TimeToBack", 10);
		FIGHT_CLUB_ANNOUNCE_RATE = eventFightClubSettings.getProperty("AnnounceRate", false);
		FIGHT_CLUB_ANNOUNCE_RATE_TO_SCREEN = eventFightClubSettings.getProperty("AnnounceRateToAllScreen", false);
		FIGHT_CLUB_ANNOUNCE_START_TO_SCREEN = eventFightClubSettings.getProperty("AnnounceStartBatleToAllScreen", false);
		FIGHT_CLUB_ANNOUNCE_TOP_KILLER = eventFightClubSettings.getProperty("FightClubAnnounceTopKiller", false);
		FIGHT_CLUB_SUMMON_LOSE_BUFFS_ON_DEATH = eventFightClubSettings.getProperty("SummonLoseBuffsOnDeath", true);
		EVENT_KOREAN_RESET_REUSE = eventFightClubSettings.getProperty("Korean_ResetReuse", false);
	}

	public static void loadRatesConfig()
	{
		ExProperties ratesSettings = load(RATES_FILE);

		RATE_XP = ratesSettings.getProperty("RateXp", 1.);
		RATE_SP = ratesSettings.getProperty("RateSp", 1.);
		RATE_DROP_ADENA = ratesSettings.getProperty("RateDropAdena", 1.);
		RATE_DROP_ITEMS = ratesSettings.getProperty("RateDropItems", 1.);
		RATE_QUESTS_REWARD = ratesSettings.getProperty("RateQuestsReward", 1.);
		RATE_QUESTS_DROP = ratesSettings.getProperty("RateQuestsDrop", 1.);
		RATE_DROP_CHAMPION = ratesSettings.getProperty("RateDropChampion", 1.);
		RATE_CLAN_REP_SCORE = ratesSettings.getProperty("RateClanRepScore", 1.);
		RATE_CLAN_REP_SCORE_MAX_AFFECTED = ratesSettings.getProperty("RateClanRepScoreMaxAffected", 2);
		RATE_CHAMPION_DROP_ADENA = ratesSettings.getProperty("RateChampionDropAdena", 1.);
		RATE_DROP_SPOIL_CHAMPION = ratesSettings.getProperty("RateSpoilChampion", 1.);
		RATE_CHANCE_GROUP_DROP_ITEMS = ratesSettings.getProperty("RateChanceGroupDropItems", 1.);
		RATE_CHANCE_DROP_ITEMS = ratesSettings.getProperty("RateChanceDropItems", 1.);
		RATE_CHANCE_DROP_HERBS = ratesSettings.getProperty("RateChanceDropHerbs", 1.);
		RATE_CHANCE_SPOIL = ratesSettings.getProperty("RateChanceSpoil", 1.);
		RATE_CHANCE_SPOIL_WEAPON_ARMOR_ACCESSORY = ratesSettings.getProperty("RateChanceSpoilWAA", 1.);
		RATE_CHANCE_DROP_WEAPON_ARMOR_ACCESSORY = ratesSettings.getProperty("RateChanceDropWAA", 1.);
		RATE_CHANCE_DROP_EPOLET = ratesSettings.getProperty("RateChanceDropEpolets", 1.);
		RATE_DROP_COMMON_ITEMS = ratesSettings.getProperty("RateDropCommonItems", 1.);
		RATE_DROP_RAIDBOSS = ratesSettings.getProperty("RateRaidBoss", 1.);
		RATE_DROP_SPOIL = ratesSettings.getProperty("RateDropSpoil", 1.);
		RATE_DROP_SIEGE_GUARD = ratesSettings.getProperty("RateSiegeGuard", 1.);
		RATE_MANOR = ratesSettings.getProperty("RateManor", 1.);
		RATE_FISH_DROP_COUNT = ratesSettings.getProperty("RateFishDropCount", 1.);
		RATE_PARTY_MIN = ratesSettings.getProperty("RatePartyMin", false);
		RATE_HELLBOUND_CONFIDENCE = ratesSettings.getProperty("RateHellboundConfidence", 1.);
		RATE_MOB_SPAWN = ratesSettings.getProperty("RateMobSpawn", 1);
		RATE_MOB_SPAWN_MIN_LEVEL = ratesSettings.getProperty("RateMobMinLevel", 1);
		RATE_MOB_SPAWN_MAX_LEVEL = ratesSettings.getProperty("RateMobMaxLevel", 100);
		CHAMPION_DROP_ONLY_ADENA = ratesSettings.getProperty("ChampionDropOnlyAdena", false);
		NO_RATE_KEY_MATERIAL = ratesSettings.getProperty("NoRateKeyMaterial", true);
		NO_RATE_RECIPES = ratesSettings.getProperty("NoRateRecipes", true);
		NO_RATE_ITEMS = ratesSettings.getProperty("NoRateItemIds", new int[]
			{
			6660,
			6662,
			6661,
			6659,
			6656,
			6658,
			8191,
			6657,
			10170,
			10314,
			16025,
			16026
			});
		NO_DROP_ITEMS = new GArray<String>();
		String NO_DROP = ratesSettings.getProperty("NoDropItems", "Vesper,Elegia,Vorpal");
		String[] NO_DROPS = NO_DROP.split(",", -1);
		for (String item : NO_DROPS)
		{
			NO_DROP_ITEMS.add(item);
		}
		_log.info("Loaded " + NO_DROP_ITEMS.size() + " block drop items.");
		NO_RATE_EQUIPMENT = ratesSettings.getProperty("NoRateEquipment", true);
	}

	public static void loadBossConfig()
	{
		ExProperties bossSettings = load(BOSS_FILE);

		RATE_RAID_REGEN = bossSettings.getProperty("RateRaidRegen", 1.);
		RATE_RAID_DEFENSE = bossSettings.getProperty("RateRaidDefense", 1.);
		RATE_RAID_ATTACK = bossSettings.getProperty("RateRaidAttack", 1.);
		RATE_EPIC_DEFENSE = bossSettings.getProperty("RateEpicDefense", RATE_RAID_DEFENSE);
		RATE_EPIC_ATTACK = bossSettings.getProperty("RateEpicAttack", RATE_RAID_ATTACK);
		RAID_MAX_LEVEL_DIFF = bossSettings.getProperty("RaidMaxLevelDiff", 8);
		MUTATED_ELPY_COUNT = bossSettings.getProperty("MutatedElpyCount", 16);
		FRINTEZZA_ALL_MEMBERS_NEED_SCROLL = bossSettings.getProperty("FrintezzaAllMembersNeedScroll", true);
	}

	public static void loadDonateConfig()
	{
		ExProperties donateConfig = load(DONATE_CONFIG_FILE);

		DONATE_ID = donateConfig.getProperty("DonateId", 37000);
		DONATOR_NPC_ITEM = donateConfig.getProperty("DonatorNPCitem", 6673);
		DONATOR_NPC_ITEM_NAME = donateConfig.getProperty("DonatorNPCitemName", "Donator Coin");
		DONATOR_NPC_COUNT_FAME = donateConfig.getProperty("DonateFame", 10000);
		DONATOR_NPC_FAME = donateConfig.getProperty("DonateCountFame", 5);
		DONATOR_NPC_COUNT_REP = donateConfig.getProperty("DonateRep", 10000);
		DONATOR_NPC_REP = donateConfig.getProperty("DonateCountClanRep", 5);
		DONATOR_NPC_COUNT_NOBLESS = donateConfig.getProperty("DonateCountNobless", 5);
		DONATOR_NPC_COUNT_SEX = donateConfig.getProperty("DonateCountChangeSex", 5);
		DONATOR_NPC_COUNT_LEVEL = donateConfig.getProperty("DonateCountMaxLevel", 5);

	}

	public static void loadDonationStore()
	{
		ExProperties DonationStore = load(DONATION_STORE);

		SERVICES_AUGMENTATION_PRICE = DonationStore.getProperty("AugmentationPrice", 50);
		SERVICES_AUGMENTATION_ITEM = DonationStore.getProperty("AugmentationItem", 37000);
		final String[] augs = DonationStore.getProperty("AugmentationDisabledList", "0").trim().split(",");
		for (String aug : augs)
		{
			if (!aug.isEmpty())
				SERVICES_AUGMENTATION_DISABLED_LIST.add(Integer.parseInt(aug.trim()));
		}
		SERVICES_CHANGE_NICK_ALLOW_SYMBOL = DonationStore.getProperty("NickChangeAllowSimbol", false);
		SERVICES_CHANGE_NICK_ENABLED = DonationStore.getProperty("NickChangeEnabled", false);
		SERVICES_CHANGE_NICK_PRICE = DonationStore.getProperty("NickChangePrice", 100);
		SERVICES_CHANGE_NICK_ITEM = DonationStore.getProperty("NickChangeItem", 37000);
		SERVICES_CHANGE_CLAN_NAME_ENABLED = DonationStore.getProperty("ClanNameChangeEnabled", false);
		SERVICES_CHANGE_CLAN_NAME_PRICE = DonationStore.getProperty("ClanNameChangePrice", 100);
		SERVICES_CHANGE_CLAN_NAME_ITEM = DonationStore.getProperty("ClanNameChangeItem", 4037);
		SERVICES_LEVEL_UP_ENABLE = DonationStore.getProperty("LevelChangeEnabled", false);
		SERVICES_DELEVEL_ENABLE = DonationStore.getProperty("DeLevelChangeEnabled", false);
		SERVICES_LEVEL_UP = DonationStore.getProperty("LevelUp", new int[] { 37000, 1 });
		SERVICES_DELEVEL = DonationStore.getProperty("LevelDown", new int[] { 37000, 1 });
		SERVICES_BUY_RECOMMENDS_ENABLED = DonationStore.getProperty("BuyRecommendsEnabled", false);
		SERVICES_BUY_RECOMMENDS_PRICE = DonationStore.getProperty("BuyRecommendsPrice", 50);
		SERVICES_BUY_RECOMMENDS_ITEM = DonationStore.getProperty("BuyRecommendsItem", 37000);
		SERVICES_BUY_CLAN_REPUTATION_ENABLED = DonationStore.getProperty("BuyClanReputationEnabled", false);
		SERVICES_BUY_CLAN_REPUTATION_PRICE = DonationStore.getProperty("BuyClanReputationPrice", 100);
		SERVICES_BUY_CLAN_REPUTATION_ITEM = DonationStore.getProperty("BuyClanReputationItem", 37000);
		SERVICES_BUY_CLAN_REPUTATION_COUNT = DonationStore.getProperty("BuyClanReputationCount", 40000);
		SERVICES_BUY_FAME_ENABLED = DonationStore.getProperty("BuyFameEnabled", false);
		SERVICES_BUY_FAME_PRICE = DonationStore.getProperty("BuyFamePrice", 100);
		SERVICES_BUY_FAME_ITEM = DonationStore.getProperty("BuyFameItem", 37000);
		SERVICES_BUY_FAME_COUNT = DonationStore.getProperty("BuyFameCount", 37000);
		SERVICES_NOBLESS_SELL_ENABLED = DonationStore.getProperty("NoblessSellEnabled", false);
		SERVICES_NOBLESS_SELL_PRICE = DonationStore.getProperty("NoblessSellPrice", 1000);
		SERVICES_NOBLESS_SELL_ITEM = DonationStore.getProperty("NoblessSellItem", 4037);
		DONATE_NOBLESS_ENABLE =  DonationStore.getProperty("DonateNoblessEnabled", false);
		DONATE_NOBLESS_SELL_ITEM = DonationStore.getProperty("DonateNoblessItemId", 37000);
		DONATE_NOBLESS_SELL_PRICE = DonationStore.getProperty("DonateNoblessItemCont", 100);
		SERVICES_CLAN_LEVEL_ENABLED = DonationStore.getProperty("ClanLvlService", true);
		SERVICES_CLAN_LEVEL_ITEM = DonationStore.getProperty("ClanLvLItem", 37000);
		SERVICES_CLAN_LEVEL_8_PRICE = DonationStore.getProperty("ClanLvl8Price", 150);
		SERVICES_CLAN_LEVEL_9_PRICE = DonationStore.getProperty("ClanLvl9Price", 400);
		SERVICES_CLAN_LEVEL_10_PRICE = DonationStore.getProperty("ClanLvl10Price", 650);
		SERVICES_CLAN_LEVEL_11_PRICE = DonationStore.getProperty("ClanLvl11Price", 900);
		SERVICES_CLAN_SKILLS_ENABLED = DonationStore.getProperty("ClanSkillsService", true);
		SERVICES_CLAN_SKILLS_ITEM = DonationStore.getProperty("ClanSkillsItem", 37000);
		SERVICES_CLAN_SKILLS_8_PRICE = DonationStore.getProperty("ClanSkillLvl8Price", 150);
		SERVICES_CLAN_SKILLS_9_PRICE = DonationStore.getProperty("ClanSkillLvl9Price", 400);
		SERVICES_CLAN_SKILLS_10_PRICE = DonationStore.getProperty("ClanSkillLvl10Price", 650);
		SERVICES_CLAN_SKILLS_11_PRICE = DonationStore.getProperty("ClanSkillLvl11Price", 900);
		SERVICES_OLF_STORE_ENABLED = DonationStore.getProperty("OlfStoreService", true);
		SERVICES_OLF_STORE_ITEM = DonationStore.getProperty("OlfStoreItem", 37000);
		SERVICES_OLF_STORE_0_PRICE = DonationStore.getProperty("OlfStore0", 100);
		SERVICES_OLF_STORE_6_PRICE = DonationStore.getProperty("OlfStore6", 200);
		SERVICES_OLF_STORE_7_PRICE = DonationStore.getProperty("OlfStore7", 275);
		SERVICES_OLF_STORE_8_PRICE = DonationStore.getProperty("OlfStore8", 350);
		SERVICES_OLF_STORE_9_PRICE = DonationStore.getProperty("OlfStore9", 425);
		SERVICES_OLF_STORE_10_PRICE = DonationStore.getProperty("OlfStore10", 500);
		SERVICES_OLF_TRANSFER_ENABLED = DonationStore.getProperty("OlfTransfer", true);
		SERVICES_OLF_TRANSFER_ITEM = DonationStore.getProperty("OlfTransferItem", new int[] { 10639, 100 });
		SERVICES_SOUL_CLOAK_TRANSFER_ENABLED = DonationStore.getProperty("SCTransfer", true);
		SERVICES_SOUL_CLOAK_TRANSFER_ITEM = DonationStore.getProperty("SCTransferItem", new int[] { 37000, 50 });
		SERVICES_EXCHANGE_EQUIP = DonationStore.getProperty("ExchangeEquipService", true);
		SERVICES_EXCHANGE_EQUIP_ITEM = DonationStore.getProperty("ExchangeEquipItem", 37000);
		SERVICES_EXCHANGE_EQUIP_ITEM_PRICE = DonationStore.getProperty("ExchangeEquipPrice", 50);
		SERVICES_EXCHANGE_UPGRADE_EQUIP_ITEM = DonationStore.getProperty("ExchangeUpgradeEquipItem", 37000);
		SERVICES_EXCHANGE_UPGRADE_EQUIP_ITEM_PRICE = DonationStore.getProperty("ExchangeUpgradeEquipPrice", 50);
		SERVICES_UNBAN_ITEM = DonationStore.getProperty("UnbanItem", new int[] { 37000, 150 });
	}

	public static void loadNpcConfig()
	{
		ExProperties npcSettings = load(NPC_FILE);

		MIN_NPC_ANIMATION = npcSettings.getProperty("MinNPCAnimation", 5);
		MAX_NPC_ANIMATION = npcSettings.getProperty("MaxNPCAnimation", 90);
		SERVER_SIDE_NPC_NAME = npcSettings.getProperty("ServerSideNpcName", false);
		SERVER_SIDE_NPC_TITLE = npcSettings.getProperty("ServerSideNpcTitle", false);
		SERVER_SIDE_NPC_TITLE_ETC = npcSettings.getProperty("ServerSideNpcTitleEtc", false);
	}

	public static void loadOtherConfig()
	{
		ExProperties otherSettings = load(OTHER_CONFIG_FILE);

		VOTE_REWARD_MSG = otherSettings.getProperty("VoteMsg", "");
		DEEPBLUE_DROP_RULES = otherSettings.getProperty("UseDeepBlueDropRules", true);
		DEEPBLUE_DROP_MAXDIFF = otherSettings.getProperty("DeepBlueDropMaxDiff", 8);
		DEEPBLUE_DROP_RAID_MAXDIFF = otherSettings.getProperty("DeepBlueDropRaidMaxDiff", 2);
		SWIMING_SPEED = otherSettings.getProperty("SwimingSpeedTemplate", 50);
		SELL_ALL_ITEMS_FREE = otherSettings.getProperty("SellAllItemsFree", false);
		INVENTORY_MAXIMUM_NO_DWARF = otherSettings.getProperty("MaximumSlotsForNoDwarf", 80);
		INVENTORY_MAXIMUM_DWARF = otherSettings.getProperty("MaximumSlotsForDwarf", 100);
		INVENTORY_MAXIMUM_GM = otherSettings.getProperty("MaximumSlotsForGMPlayer", 250);
		QUEST_INVENTORY_MAXIMUM = otherSettings.getProperty("MaximumSlotsForQuests", 100);

		MULTISELL_SIZE = otherSettings.getProperty("MultisellPageSize", 10);
		WAREHOUSE_SLOTS_NO_DWARF = otherSettings.getProperty("BaseWarehouseSlotsForNoDwarf", 100);
		WAREHOUSE_SLOTS_DWARF = otherSettings.getProperty("BaseWarehouseSlotsForDwarf", 120);
		WAREHOUSE_SLOTS_CLAN = otherSettings.getProperty("MaximumWarehouseSlotsForClan", 200);
		FREIGHT_SLOTS = otherSettings.getProperty("MaximumFreightSlots", 10);
		ENCHANT_REUSE = otherSettings.getProperty("EnchantReuse", 7);
		ENCHANT_SPAM_KICK = otherSettings.getProperty("EnchantSpamKick", false);
		ENCHANT_SPAM_LOGS = otherSettings.getProperty("EnchantSpamLogs", false);
		ENCHANT_CHANCE_WEAPON = otherSettings.getProperty("EnchantChance", 66);
		ENCHANT_CHANCE_ARMOR = otherSettings.getProperty("EnchantChanceArmor", ENCHANT_CHANCE_WEAPON);
		ENCHANT_CHANCE_ACCESSORY = otherSettings.getProperty("EnchantChanceAccessory", ENCHANT_CHANCE_ARMOR);
		ENCHANT_CHANCE_CRYSTAL_WEAPON = otherSettings.getProperty("EnchantChanceCrystal", 66);
		ENCHANT_CHANCE_CRYSTAL_ARMOR = otherSettings.getProperty("EnchantChanceCrystalArmor", ENCHANT_CHANCE_CRYSTAL_WEAPON);
		ENCHANT_CHANCE_CRYSTAL_ARMOR_OLF = otherSettings.getProperty("EnchantChanceCrystalArmorOlf", 66);
		ENCHANT_CHANCE_CRYSTAL_ACCESSORY = otherSettings.getProperty("EnchantChanceCrystalAccessory", ENCHANT_CHANCE_CRYSTAL_ARMOR);
		SAFE_ENCHANT_COMMON = otherSettings.getProperty("SafeEnchantCommon", 3);
		SAFE_ENCHANT_FULL_BODY = otherSettings.getProperty("SafeEnchantFullBody", 4);
		ENCHANT_MAX = otherSettings.getProperty("EnchantMax", 20);
		SAFE_ENCHANT_LVL = otherSettings.getProperty("SafeEnchant", 0);
		ARMOR_OVERENCHANT_HPBONUS_LIMIT = otherSettings.getProperty("ArmorOverEnchantHPBonusLimit", 10) - 3;
		SHOW_ENCHANT_EFFECT_RESULT = otherSettings.getProperty("ShowEnchantEffectResult", false);
		ENCHANT_CHANCE_WEAPON_BLESS = otherSettings.getProperty("EnchantChanceBless", 66);
		ENCHANT_CHANCE_ARMOR_BLESS = otherSettings.getProperty("EnchantChanceArmorBless", ENCHANT_CHANCE_WEAPON);
		ENCHANT_CHANCE_ACCESSORY_BLESS = otherSettings.getProperty("EnchantChanceAccessoryBless", ENCHANT_CHANCE_ARMOR);
		USE_ALT_ENCHANT = Boolean.parseBoolean(otherSettings.getProperty("UseAltEnchant", "False"));
		OLF_TSHIRT_CUSTOM_ENABLED = Boolean.parseBoolean(otherSettings.getProperty("EnableOlfTShirtEnchant", "False"));
		for (String prop : otherSettings.getProperty("EnchantWeaponFighter", "100,100,100,70,70,70,70,70,70,70,70,70,70,70,70,35,35,35,35,35").split(","))
		{
			ENCHANT_WEAPON_FIGHT.add(Integer.parseInt(prop));
		}
		for (String prop : otherSettings.getProperty("EnchantWeaponFighterCrystal", "100,100,100,70,70,70,70,70,70,70,70,70,70,70,70,35,35,35,35,35").split(","))
		{
			ENCHANT_WEAPON_FIGHT_BLESSED.add(Integer.parseInt(prop));
		}
		for (String prop : otherSettings.getProperty("EnchantWeaponFighterBlessed", "100,100,100,70,70,70,70,70,70,70,70,70,70,70,70,35,35,35,35,35").split(","))
		{
			ENCHANT_WEAPON_FIGHT_CRYSTAL.add(Integer.parseInt(prop));
		}
		for (String prop : otherSettings.getProperty("EnchantArmor", "100,100,100,66,33,25,20,16,14,12,11,10,9,8,8,7,7,6,6,6").split(","))
		{
			ENCHANT_ARMOR.add(Integer.parseInt(prop));
		}
		for (String prop : otherSettings.getProperty("EnchantArmorCrystal", "100,100,100,66,33,25,20,16,14,12,11,10,9,8,8,7,7,6,6,6").split(","))
		{
			ENCHANT_ARMOR_CRYSTAL.add(Integer.parseInt(prop));
		}
		for (String prop : otherSettings.getProperty("EnchantArmorBlessed", "100,100,100,66,33,25,20,16,14,12,11,10,9,8,8,7,7,6,6,6").split(","))
		{
			ENCHANT_ARMOR_BLESSED.add(Integer.parseInt(prop));
		}
		for (String prop : otherSettings.getProperty("EnchantJewelry", "100,100,100,66,33,25,20,16,14,12,11,10,9,8,8,7,7,6,6,6").split(","))
		{
			ENCHANT_ARMOR_JEWELRY.add(Integer.parseInt(prop));
		}
		for (String prop : otherSettings.getProperty("EnchantJewelryCrystal", "100,100,100,66,33,25,20,16,14,12,11,10,9,8,8,7,7,6,6,6").split(","))
		{
			ENCHANT_ARMOR_JEWELRY_CRYSTAL.add(Integer.parseInt(prop));
		}
		for (String prop : otherSettings.getProperty("EnchantJewelryBlessed", "100,100,100,66,33,25,20,16,14,12,11,10,9,8,8,7,7,6,6,6").split(","))
		{
			ENCHANT_ARMOR_JEWELRY_BLESSED.add(Integer.parseInt(prop));
		}

		for (String prop : otherSettings.getProperty("EnchantOlfTShirtChances", "100,100,100,50,40,30,20,10,10").split(","))
		{
			ENCHANT_OLF_TSHIRT_CHANCES.add(Integer.parseInt(prop));
		}
		ENCHANT_ATTRIBUTE_STONE_CHANCE = otherSettings.getProperty("EnchantAttributeChance", 50);
		ENCHANT_ATTRIBUTE_CRYSTAL_CHANCE = otherSettings.getProperty("EnchantAttributeCrystalChance", 30);
		REGEN_SIT_WAIT = otherSettings.getProperty("RegenSitWait", false);
		HTML_WELCOME = otherSettings.getProperty("ShowHTMLWelcome", false);
		STARTING_ADENA = otherSettings.getProperty("StartingAdena", 0);
		UNSTUCK_SKILL = otherSettings.getProperty("UnstuckSkill", true);
		RESPAWN_RESTORE_CP = otherSettings.getProperty("RespawnRestoreCP", 0.) / 100;
		RESPAWN_RESTORE_HP = otherSettings.getProperty("RespawnRestoreHP", 65.) / 100;
		RESPAWN_RESTORE_MP = otherSettings.getProperty("RespawnRestoreMP", 0.) / 100;
		MAX_PVTSTORE_SLOTS_DWARF = otherSettings.getProperty("MaxPvtStoreSlotsDwarf", 5);
		MAX_PVTSTORE_SLOTS_OTHER = otherSettings.getProperty("MaxPvtStoreSlotsOther", 4);
		MAX_PVTCRAFT_SLOTS = otherSettings.getProperty("MaxPvtManufactureSlots", 20);
		SENDSTATUS_TRADE_JUST_OFFLINE = otherSettings.getProperty("SendStatusTradeJustOffline", false);
		SENDSTATUS_TRADE_MOD = otherSettings.getProperty("SendStatusTradeMod", 1.);
		SHOW_OFFLINE_MODE_IN_ONLINE = otherSettings.getProperty("ShowOfflineTradeInOnline", false);
		ANNOUNCE_MAMMON_SPAWN = otherSettings.getProperty("AnnounceMammonSpawn", true);
		GM_NAME_COLOUR = Integer.decode("0x" + otherSettings.getProperty("GMNameColour", "FFFFFF"));
		GM_HERO_AURA = otherSettings.getProperty("GMHeroAura", false);
		NORMAL_NAME_COLOUR = Integer.decode("0x" + otherSettings.getProperty("NormalNameColour", "FFFFFF"));
		CLANLEADER_NAME_COLOUR = Integer.decode("0x" + otherSettings.getProperty("ClanleaderNameColour", "FFFFFF"));
		GAME_POINT_ITEM_ID = otherSettings.getProperty("GamePointItemId", -1);
		STARTING_LVL = otherSettings.getProperty("StartingLvL", 0);
		MAX_PLAYER_CONTRIBUTION = otherSettings.getProperty("MaxPlayerContribution", 1000000);
		ENCHANT_MAX_DIVINE_SCROLL_WEAPON = otherSettings.getProperty("EnchantMaxDivineScrollWeapon", 20);
		ENCHANT_MAX_DIVINE_SCROLL_ARMOR = otherSettings.getProperty("EnchantMaxDivineScrollArmor", 20);
		ENCHANT_MAX_DIVINE_SCROLL_JEWELRY = otherSettings.getProperty("EnchantMaxDivineScrollJewelry", 20);
		ENCHANT_MAX_WEAPON = otherSettings.getProperty("EnchantMaxWeapon", 20);
		ENCHANT_MAX_ARMOR = otherSettings.getProperty("EnchantMaxArmor", 20);
		ENCHANT_MAX_JEWELRY = otherSettings.getProperty("EnchantMaxJewelry", 20);
		ENCHANT_MAX_OLF_T_SHIRT = otherSettings.getProperty("EnchantMaxOlfTShirt", 10);
		CAPTCHA_ALLOW = otherSettings.getProperty("AllowCaptcha", false);
		CAPTCHA_ANSWER_SECONDS = otherSettings.getProperty("CaptchaAnswerTime", 15L);
		CAPTCHA_JAIL_SECONDS = otherSettings.getProperty("CaptchaJailTime", 1800L);
		CAPTCHA_TIME_BETWEEN_TESTED_SECONDS = otherSettings.getProperty("CaptchaDelayBetweenCaptchas", 1800);
		CAPTCHA_TIME_BETWEEN_REPORTS_SECONDS = otherSettings.getProperty("CaptchaReportDelay", 7200);
		CAPTCHA_MIN_LEVEL = otherSettings.getProperty("CaptchaMinLevel", 40);
		CAPTCHA_COUNT = otherSettings.getProperty("CaptchaCount", 2);
		CAPTCHA_PUNISHMENT = otherSettings.getProperty("CaptchaPunishment", new String[] { "JAIL:90", "JAIL:350", "JAIL:900", "BAN:-100" });
		ENABLE_PLAYER_ITEM_LOGS = otherSettings.getProperty("EnablePlayerItemLogs", false);
		PLAYER_ITEM_LOGS_SAVED_IN_DB = otherSettings.getProperty("PlayerItemLogsSavedInDB", false);
		PLAYER_ITEM_LOGS_MAX_TIME = otherSettings.getProperty("PlayerItemLogsMaxTime", 172800000L);
		DEBUFF_PROTECTION_SYSTEM = otherSettings.getProperty("DebuffProtectionSystem", false);
		BUFF_RETURN_OLYMPIAD_TIME = otherSettings.getProperty("BuffReturnOlympiadTime", -1);
		BUFF_RETURN_AUTO_EVENTS_TIME = otherSettings.getProperty("BuffReturnAutoEventsTime", -1);
		BUFF_RETURN_NORMAL_LOCATIONS_TIME = otherSettings.getProperty("BuffReturnNormalLocationsTime", -1);
		ENABLE_SPECIAL_TUTORIAL = otherSettings.getProperty("EnableSpecialTutorial", false);
		ENABLE_EMOTIONS = otherSettings.getProperty("EnableEmotions", false);
		ALLOW_SKILL_ENCHANTING_OUTSIDE_PEACE_ZONE = otherSettings.getProperty("AllowSkillEnchantingOutsidePeaceZone", true);
		ALLOW_MACROS_REUSE_BUG = otherSettings.getProperty("AllowMacrosReuseBug", false);
		ALLOW_MACROS_ENCHANT_BUG = otherSettings.getProperty("AllowMacrosEnchantBug", false);
	}

	public static void loadSpoilConfig()
	{
		ExProperties spoilSettings = load(SPOIL_CONFIG_FILE);

		BASE_SPOIL_RATE = spoilSettings.getProperty("BasePercentChanceOfSpoilSuccess", 78.);
		MINIMUM_SPOIL_RATE = spoilSettings.getProperty("MinimumPercentChanceOfSpoilSuccess", 1.);
		ALT_SPOIL_FORMULA = spoilSettings.getProperty("AltFormula", false);
		MANOR_SOWING_BASIC_SUCCESS = spoilSettings.getProperty("BasePercentChanceOfSowingSuccess", 100.);
		MANOR_SOWING_ALT_BASIC_SUCCESS = spoilSettings.getProperty("BasePercentChanceOfSowingAltSuccess", 10.);
		MANOR_HARVESTING_BASIC_SUCCESS = spoilSettings.getProperty("BasePercentChanceOfHarvestingSuccess", 90.);
		MANOR_DIFF_PLAYER_TARGET = spoilSettings.getProperty("MinDiffPlayerMob", 5);
		MANOR_DIFF_PLAYER_TARGET_PENALTY = spoilSettings.getProperty("DiffPlayerMobPenalty", 5.);
		MANOR_DIFF_SEED_TARGET = spoilSettings.getProperty("MinDiffSeedMob", 5);
		MANOR_DIFF_SEED_TARGET_PENALTY = spoilSettings.getProperty("DiffSeedMobPenalty", 5.);
		ALLOW_MANOR = spoilSettings.getProperty("AllowManor", true);
		MANOR_REFRESH_TIME = spoilSettings.getProperty("AltManorRefreshTime", 20);
		MANOR_REFRESH_MIN = spoilSettings.getProperty("AltManorRefreshMin", 00);
		MANOR_APPROVE_TIME = spoilSettings.getProperty("AltManorApproveTime", 6);
		MANOR_APPROVE_MIN = spoilSettings.getProperty("AltManorApproveMin", 00);
		MANOR_MAINTENANCE_PERIOD = spoilSettings.getProperty("AltManorMaintenancePeriod", 360000);
	}

	public static void loadInstancesConfig()
	{
		ExProperties instancesSettings = load(INSTANCES_FILE);

		ALLOW_INSTANCES_LEVEL_MANUAL = instancesSettings.getProperty("AllowInstancesLevelManual", false);
		ALLOW_INSTANCES_PARTY_MANUAL = instancesSettings.getProperty("AllowInstancesPartyManual", false);
		INSTANCES_LEVEL_MIN = instancesSettings.getProperty("InstancesLevelMin", 1);
		INSTANCES_LEVEL_MAX = instancesSettings.getProperty("InstancesLevelMax", 85);
		INSTANCES_PARTY_MIN = instancesSettings.getProperty("InstancesPartyMin", 1);
		INSTANCES_PARTY_MAX = instancesSettings.getProperty("InstancesPartyMax", 9);
	}

	public static void loadEpicBossConfig()
	{
		ExProperties epicBossSettings = load(EPIC_BOSS_FILE);

		ANTHARAS_DEFAULT_SPAWN_HOURS = epicBossSettings.getProperty("AntharasDefaultSpawnHours", 168);
		ANTHARAS_RANDOM_SPAWN_HOURS = epicBossSettings.getProperty("AntharasRandomSpawnHours", 8);
		VALAKAS_DEFAULT_SPAWN_HOURS = epicBossSettings.getProperty("ValakasDefaultSpawnHours", 240);
		VALAKAS_RANDOM_SPAWN_HOURS = epicBossSettings.getProperty("ValakasRandomSpawnHours", 24);
		BAIUM_DEFAULT_SPAWN_HOURS = epicBossSettings.getProperty("BaiumDefaultSpawnHours", 120);
		BAIUM_RANDOM_SPAWN_HOURS = epicBossSettings.getProperty("BaiumRandomSpawnHours", 8);
		FIXINTERVALOFBAYLORSPAWN_HOUR = epicBossSettings.getProperty("BaylorDefaultSpawnHours", 24);
		RANDOMINTERVALOFBAYLORSPAWN = epicBossSettings.getProperty("BaylorRandomSpawnHours", 24);
		FIXINTERVALOFBELETHSPAWN_HOUR = epicBossSettings.getProperty("BelethDefaultSpawnHours", 48);
		BELETH_CLONES_RESPAWN_TIME = epicBossSettings.getProperty("BelethClonesRespawnTime", 40);
		MIN_PLAYERS_TO_SPAWN_BELETH = epicBossSettings.getProperty("MinPlayersToSpawnBeleth", 18);
		FIXINTERVALOFSAILRENSPAWN_HOUR = epicBossSettings.getProperty("SailrenDefaultSpawnHours", 24);
		RANDOMINTERVALOFSAILRENSPAWN = epicBossSettings.getProperty("SailrenRandomSpawnHours", 24);
		
		ALLOW_DUALBOX_EPIC = epicBossSettings.getProperty("AllowDualBoxEpic", true);
	}

	public static void loadFormulasConfig()
	{
		ExProperties formulasSettings = load(FORMULAS_CONFIGURATION_FILE);

		SKILLS_CHANCE_MOD = formulasSettings.getProperty("SkillsChanceMod", 11.);
		SKILLS_CHANCE_POW = formulasSettings.getProperty("SkillsChancePow", 0.5);
		SKILLS_CHANCE_MIN = formulasSettings.getProperty("SkillsChanceMin", 5.);
		SKILLS_CHANCE_CAP = formulasSettings.getProperty("SkillsChanceCap", 95.);
		SKILLS_MOB_CHANCE = formulasSettings.getProperty("SkillsMobChance", 0.5);
		SKILLS_DEBUFF_MOB_CHANCE = formulasSettings.getProperty("SkillsDebuffMobChance", 0.5);
		SKILLS_CAST_TIME_MIN = formulasSettings.getProperty("SkillsCastTimeMin", 333);
		SKILLS_DELTA_MOD_MULT = formulasSettings.getProperty("SkillsDeltaModMult", 0.06);
		SKILLS_ATTACKER_WEAPON_MOD = formulasSettings.getProperty("SkillsAttackerWeaponMod", 1.95);
		SKILLS_M_ATK_MOD_MAX = formulasSettings.getProperty("SkillsMAtkModMax", 1.3);
		SKILLS_M_ATK_MOD_MIN = formulasSettings.getProperty("SkillsMAtkModMin", 0.7);
		SKILLS_ELEMENT_MOD_MULT = formulasSettings.getProperty("SkillsElementModMult", 0.2);
		SKILLS_ELEMENT_MOD_MAX = formulasSettings.getProperty("SkillsElementModMax", 1.2);
		SKILLS_ELEMENT_MOD_MIN = formulasSettings.getProperty("SkillsElementModMin", 0.8);
		SKILLS_CALC_STAT_MOD = formulasSettings.getProperty("SkillsCalcStatMod", true);
		ALT_ABSORB_DAMAGE_MODIFIER = formulasSettings.getProperty("AbsorbDamageModifier", 1.0);
		LIM_PATK = formulasSettings.getProperty("LimitPatk", 20000);
		LIM_MATK = formulasSettings.getProperty("LimitMAtk", 25000);
		LIM_PDEF = formulasSettings.getProperty("LimitPDef", 15000);
		LIM_MDEF = formulasSettings.getProperty("LimitMDef", 15000);
		LIM_PATK_SPD = formulasSettings.getProperty("LimitPatkSpd", 1500);
		LIM_MATK_SPD = formulasSettings.getProperty("LimitMatkSpd", 1999);
		LIM_CRIT_DAM = formulasSettings.getProperty("LimitCriticalDamage", 2000);
		LIM_CRIT = formulasSettings.getProperty("LimitCritical", 500);
		LIM_MCRIT = formulasSettings.getProperty("LimitMCritical", 20);
		LIM_ACCURACY = formulasSettings.getProperty("LimitAccuracy", 200);
		LIM_EVASION = formulasSettings.getProperty("LimitEvasion", 200);
		LIM_MOVE = formulasSettings.getProperty("LimitMove", 250);
		GM_LIM_MOVE = formulasSettings.getProperty("GmLimitMove", 1500);
		LIM_FAME = formulasSettings.getProperty("LimitFame", 50000);
		ALT_NPC_PATK_MODIFIER = formulasSettings.getProperty("NpcPAtkModifier", 1.0);
		ALT_NPC_MATK_MODIFIER = formulasSettings.getProperty("NpcMAtkModifier", 1.0);
		ALT_NPC_MAXHP_MODIFIER = formulasSettings.getProperty("NpcMaxHpModifier", 1.00);
		ALT_NPC_MAXMP_MODIFIER = formulasSettings.getProperty("NpcMapMpModifier", 1.00);
		ALT_NPC_PDEF_MODIFIER = formulasSettings.getProperty("NpcPDefModifier", 1.0);
		ALT_NPC_MDEF_MODIFIER = formulasSettings.getProperty("NpcMDefModifier", 1.0);
		ALT_POLE_DAMAGE_MODIFIER = formulasSettings.getProperty("PoleDamageModifier", 1.0);
	}

	public static void loadDevelopSettings()
	{
		ExProperties DevelopSettings = load(DEVELOP_FILE);

		ALT_DEBUG_ENABLED = DevelopSettings.getProperty("AltDebugEnabled", false);
		ALT_DEBUG_PVP_ENABLED = DevelopSettings.getProperty("AltDebugPvPEnabled", false);
		ALT_DEBUG_PVP_DUEL_ONLY = DevelopSettings.getProperty("AltDebugPvPDuelOnly", true);
		ALT_DEBUG_PVE_ENABLED = DevelopSettings.getProperty("AltDebugPvEEnabled", false);
		DONTLOADSPAWN = DevelopSettings.getProperty("StartWithoutSpawn", false);
		DONTLOADQUEST = DevelopSettings.getProperty("StartWithoutQuest", false);
		LOAD_CUSTOM_SPAWN = DevelopSettings.getProperty("LoadAddGmSpawn", false);
		SAVE_GM_SPAWN = DevelopSettings.getProperty("SaveGmSpawn", false);
	}

	public static void loadExtSettings()
	{
		ExProperties properties = load(EXT_FILE);

		EX_NEW_PETITION_SYSTEM = properties.getProperty("NewPetitionSystem", false);
		EX_JAPAN_MINIGAME = properties.getProperty("JapanMinigame", false);
		EX_LECTURE_MARK = properties.getProperty("LectureMark", false);
		ENABLE_AUTO_HUNTING_REPORT = properties.getProperty("AllowAutoHuntingReport", true);
		Random ppc = new Random();
		int z = ppc.nextInt(6);
		if (z == 0)
		{
			z += 2;
		}
		for (int x = 0; x < 8; x++)
		{
			if (x == 4)
			{
				RWHO_ARRAY[x] = 44;
			}
			else
			{
				RWHO_ARRAY[x] = 51 + ppc.nextInt(z);
			}
		}
		RWHO_ARRAY[11] = 37265 + ppc.nextInt((z * 2) + 3);
		RWHO_ARRAY[8] = 51 + ppc.nextInt(z);
		z = 36224 + ppc.nextInt(z * 2);
		RWHO_ARRAY[9] = z;
		RWHO_ARRAY[10] = z;
		RWHO_ARRAY[12] = 1;
		RWHO_LOG = properties.getProperty("RemoteWhoLog", false);
		RWHO_SEND_TRASH = properties.getProperty("RemoteWhoSendTrash", false);
		RWHO_MAX_ONLINE = properties.getProperty("RemoteWhoMaxOnline", 0);
		RWHO_KEEP_STAT = properties.getProperty("RemoteOnlineKeepStat", 5);
		RWHO_ONLINE_INCREMENT = properties.getProperty("RemoteOnlineIncrement", 0);
		RWHO_PRIV_STORE_FACTOR = properties.getProperty("RemotePrivStoreFactor", 0);
		RWHO_FORCE_INC = properties.getProperty("RemoteWhoForceInc", 0);
	}

	public static void loadItemsSettings()
	{
		ExProperties itemsProperties = load(ITEMS_FILE);

		CAN_BE_TRADED_NO_TARADEABLE = itemsProperties.getProperty("CanBeTradedNoTradeable", false);
		CAN_BE_TRADED_NO_SELLABLE = itemsProperties.getProperty("CanBeTradedNoSellable", false);
		CAN_BE_TRADED_NO_STOREABLE = itemsProperties.getProperty("CanBeTradedNoStoreable", false);
		CAN_BE_TRADED_SHADOW_ITEM = itemsProperties.getProperty("CanBeTradedShadowItem", false);
		CAN_BE_TRADED_HERO_WEAPON = itemsProperties.getProperty("CanBeTradedHeroWeapon", false);
		CAN_BE_WH_NO_TARADEABLE = itemsProperties.getProperty("CanBeWhNoTradeable", false);
		CAN_BE_CWH_NO_TARADEABLE = itemsProperties.getProperty("CanBeCwhNoTradeable", false);
		CAN_BE_CWH_IS_AUGMENTED = itemsProperties.getProperty("CanBeCwhIsAugmented", false);
		CAN_BE_WH_IS_AUGMENTED = itemsProperties.getProperty("CanBeWhIsAugmented", false);
		ALLOW_SOUL_SPIRIT_SHOT_INFINITELY = itemsProperties.getProperty("AllowSoulSpiritShotInfinitely", false);
		ALLOW_ARROW_INFINITELY = itemsProperties.getProperty("AllowArrowInfinitely", false);
		ALLOW_START_ITEMS = itemsProperties.getProperty("AllowStartItems", false);
		START_ITEMS_MAGE = itemsProperties.getProperty("StartItemsMageIds", new int[]
			{
			57
			});
		START_ITEMS_MAGE_COUNT = itemsProperties.getProperty("StartItemsMageCount", new int[]
			{
			1
			});
		START_ITEMS_FITHER = itemsProperties.getProperty("StartItemsFigtherIds", new int[]
			{
			57
			});
		START_ITEMS_FITHER_COUNT = itemsProperties.getProperty("StartItemsFigtherCount", new int[]
			{
			1
			});
	}

	public static void loadTopSettings()
	{
		ExProperties topSetting = load(TOP_FILE);

		ALLOW_HOPZONE_VOTE_REWARD = topSetting.getProperty("AllowHopzoneVoteReward", true);
		HOPZONE_SERVER_LINK = topSetting.getProperty("HopzoneServerLink", "http://l2.hopzone.net/lineage2/");
		HOPZONE_FIRST_PAGE_LINK = topSetting.getProperty("HopzoneFirstPageLink", "http://l2.hopzone.net/lineage2/");
		HOPZONE_VOTES_DIFFERENCE = topSetting.getProperty("HopzoneVotesDifference", 5);
		HOPZONE_FIRST_PAGE_RANK_NEEDED = topSetting.getProperty("HopzoneFirstPageRankNeeded", 15);
		HOPZONE_REWARD_CHECK_TIME = topSetting.getProperty("HopzoneRewardCheckTime", 5);
		HOPZONE_DUALBOXES_ALLOWED = topSetting.getProperty("HopzoneDualboxesAllowed", 1);
		ALLOW_HOPZONE_GAME_SERVER_REPORT = topSetting.getProperty("AllowHopzoneGameServerReport", true);
		ALLOW_TOPZONE_VOTE_REWARD = topSetting.getProperty("AllowTopzoneVoteReward", true);
		TOPZONE_SERVER_LINK = topSetting.getProperty("TopzoneServerLink", "http://l2.topzone.net/lineage2/");
		TOPZONE_FIRST_PAGE_LINK = topSetting.getProperty("TopzoneFirstPageLink", "http://l2.topzone.net/lineage2/");
		TOPZONE_VOTES_DIFFERENCE = topSetting.getProperty("TopzoneVotesDifference", 5);
		TOPZONE_FIRST_PAGE_RANK_NEEDED = topSetting.getProperty("TopzoneFirstPageRankNeeded", 15);
		TOPZONE_REWARD_CHECK_TIME = topSetting.getProperty("TopzoneRewardCheckTime", 5);
		TOPZONE_DUALBOXES_ALLOWED = topSetting.getProperty("TopzoneDualboxesAllowed", 1);
		ALLOW_TOPZONE_GAME_SERVER_REPORT = topSetting.getProperty("AllowTopzoneGameServerReport", true);
		HOPZONE_REWARD_ID = topSetting.getProperty("HopZoneRewardId", 6673);
		HOPZONE_REWARD_COUNT = topSetting.getProperty("HopZoneTopRewardCount", 1);
		TOPZONE_REWARD_ID = topSetting.getProperty("TopZoneRewardId", 6673);
		TOPZONE_REWARD_COUNT = topSetting.getProperty("TopZoneRewardCount", 1);
	}

	public static void loadAltSettings()
	{
		ExProperties altSettings = load(ALT_SETTINGS_FILE);
		ALT_ARENA_EXP = altSettings.getProperty("ArenaExp", true);
		AUTO_SOUL_CRYSTAL_QUEST = altSettings.getProperty("AutoSoulCrystalQuest", true);
		ALT_GAME_DELEVEL = altSettings.getProperty("Delevel", true);
		ALT_MAIL_MIN_LVL = altSettings.getProperty("MinLevelToSendMail", 0);
		VITAMIN_PETS_FOOD_ID = altSettings.getProperty("VitaminPetsFoodID", -1);
		VITAMIN_DESELOT_FOOD_ID = altSettings.getProperty("VitaminDeselotFoodID", -1);
		ALT_TELEPORTS_ONLY_FOR_GIRAN = altSettings.getProperty("AllScrollsSoEToGiran", false);
		VITAMIN_SUPERPET_FOOD_ID = altSettings.getProperty("VitaminSuperPetID", -1);
		ALT_SAVE_UNSAVEABLE = altSettings.getProperty("AltSaveUnsaveable", false);
		SHIELD_SLAM_BLOCK_IS_MUSIC = altSettings.getProperty("ShieldSlamBlockIsMusic", false);
		ALT_SAVE_EFFECTS_REMAINING_TIME = altSettings.getProperty("AltSaveEffectsRemainingTime", 5);
		ALLOW_PET_ATTACK_MASTER = altSettings.getProperty("allowPetAttackMaster", true);
		ALT_SHOW_REUSE_MSG = altSettings.getProperty("AltShowSkillReuseMessage", true);
		ALT_DELETE_SA_BUFFS = altSettings.getProperty("AltDeleteSABuffs", false);
		AUTO_LOOT = altSettings.getProperty("AutoLoot", false);
		AUTO_LOOT_ONLY_ADENA = altSettings.getProperty("AutoLootOnlyAdena", false);
		AUTO_LOOT_HERBS = altSettings.getProperty("AutoLootHerbs", false);
		AUTO_LOOT_INDIVIDUAL = altSettings.getProperty("AutoLootIndividual", false);
		AUTO_LOOT_FROM_RAIDS = altSettings.getProperty("AutoLootFromRaids", false);
		AUTO_LOOT_PK = altSettings.getProperty("AutoLootPK", false);
		ALT_GAME_KARMA_PLAYER_CAN_SHOP = altSettings.getProperty("AltKarmaPlayerCanShop", false);
		SAVING_SPS = altSettings.getProperty("SavingSpS", false);
		MANAHEAL_SPS_BONUS = altSettings.getProperty("ManahealSpSBonus", false);
		CRAFT_MASTERWORK_CHANCE = altSettings.getProperty("CraftMasterworkChance", 3.);
		CRAFT_DOUBLECRAFT_CHANCE = altSettings.getProperty("CraftDoubleCraftChance", 3.);
		ALT_RAID_RESPAWN_MULTIPLIER = altSettings.getProperty("AltRaidRespawnMultiplier", 1.0);
		ALT_ALLOW_AUGMENT_ALL = altSettings.getProperty("AugmentAll", false);
		ALT_ALLOW_DROP_AUGMENTED = altSettings.getProperty("AlowDropAugmented", false);
		ALT_GAME_UNREGISTER_RECIPE = altSettings.getProperty("AltUnregisterRecipe", true);
		ALT_GAME_SHOW_DROPLIST = altSettings.getProperty("AltShowDroplist", true);
		ALLOW_NPC_SHIFTCLICK = altSettings.getProperty("AllowShiftClick", true);
		ALT_FULL_NPC_STATS_PAGE = altSettings.getProperty("AltFullStatsPage", false);
		ALT_GAME_SUBCLASS_WITHOUT_QUESTS = altSettings.getProperty("AltAllowSubClassWithoutQuest", false);
		ALT_ALLOW_SUBCLASS_WITHOUT_BAIUM = altSettings.getProperty("AltAllowSubClassWithoutBaium", true);
		ALT_GAME_LEVEL_TO_GET_SUBCLASS = altSettings.getProperty("AltLevelToGetSubclass", 75);
		ALT_GAME_START_LEVEL_TO_SUBCLASS = altSettings.getProperty("AltStartLevelToSubclass", 40);
		ALT_GAME_SUB_ADD = altSettings.getProperty("AltSubAdd", 0);
		ALT_GAME_SUB_BOOK = altSettings.getProperty("AltSubBook", false);
		ALT_MAX_LEVEL = Math.min(altSettings.getProperty("AltMaxLevel", 85), Experience.LEVEL.length - 1);
		ALT_MAX_SUB_LEVEL = Math.min(altSettings.getProperty("AltMaxSubLevel", 80), Experience.LEVEL.length - 1);
		ALT_ALLOW_OTHERS_WITHDRAW_FROM_CLAN_WAREHOUSE = altSettings.getProperty("AltAllowOthersWithdrawFromClanWarehouse", false);
		ALT_ALLOW_CLAN_COMMAND_ONLY_FOR_CLAN_LEADER = altSettings.getProperty("AltAllowClanCommandOnlyForClanLeader", true);
		AUGMENTATION_CHANCE_MOD = altSettings.getProperty("AugmentChance", new double[] { 1.0D, 1.0D });
		ALT_GAME_REQUIRE_CLAN_CASTLE = altSettings.getProperty("AltRequireClanCastle", false);
		ALT_GAME_REQUIRE_CASTLE_DAWN = altSettings.getProperty("AltRequireCastleDawn", true);
		ALT_GAME_ALLOW_ADENA_DAWN = altSettings.getProperty("AltAllowAdenaDawn", true);
		RETAIL_SS = altSettings.getProperty("Retail_SevenSigns", true);
		ALT_ADD_RECIPES = altSettings.getProperty("AltAddRecipes", 0);
		SS_ANNOUNCE_PERIOD = altSettings.getProperty("SSAnnouncePeriod", 0);
		PETITIONING_ALLOWED = altSettings.getProperty("PetitioningAllowed", true);
		MAX_PETITIONS_PER_PLAYER = altSettings.getProperty("MaxPetitionsPerPlayer", 5);
		MAX_PETITIONS_PENDING = altSettings.getProperty("MaxPetitionsPending", 25);
		AUTO_LEARN_SKILLS = altSettings.getProperty("AutoLearnSkills", false);
		AUTO_LEARN_FORGOTTEN_SKILLS = altSettings.getProperty("AutoLearnForgottenSkills", false);
		ALT_SOCIAL_ACTION_REUSE = altSettings.getProperty("AltSocialActionReuse", false);
		ALT_DISABLE_SPELLBOOKS = altSettings.getProperty("AltDisableSpellbooks", false);
		ALT_SIMPLE_SIGNS = altSettings.getProperty("PushkinSignsOptions", false);
		ALT_TELE_TO_CATACOMBS = altSettings.getProperty("TeleToCatacombs", false);
		ALT_BS_CRYSTALLIZE = altSettings.getProperty("BSCrystallize", false);
		ALT_MAMMON_UPGRADE = altSettings.getProperty("MammonUpgrade", 6680500);
		ALT_MAMMON_EXCHANGE = altSettings.getProperty("MammonExchange", 10091400);
		ALT_ALLOW_TATTOO = altSettings.getProperty("AllowTattoo", false);
		ALT_BUFF_LIMIT = altSettings.getProperty("BuffLimit", 20);
		ALT_DEATH_PENALTY = altSettings.getProperty("EnableAltDeathPenalty", false);
		ALLOW_DEATH_PENALTY_C5 = altSettings.getProperty("EnableDeathPenaltyC5", true);
		ALT_DEATH_PENALTY_C5_CHANCE = altSettings.getProperty("DeathPenaltyC5Chance", 10);
		ALT_DEATH_PENALTY_C5_CHAOTIC_RECOVERY = altSettings.getProperty("ChaoticCanUseScrollOfRecovery", false);
		ALT_DEATH_PENALTY_C5_EXPERIENCE_PENALTY = altSettings.getProperty("DeathPenaltyC5RateExpPenalty", 1);
		ALT_DEATH_PENALTY_C5_KARMA_PENALTY = altSettings.getProperty("DeathPenaltyC5RateKarma", 1);
		ALT_PK_DEATH_RATE = altSettings.getProperty("AltPKDeathRate", 0.);
		NONOWNER_ITEM_PICKUP_DELAY = altSettings.getProperty("NonOwnerItemPickupDelay", 15L) * 1000L;
		NONOWNER_ITEM_PICKUP_DELAY_RAIDS = altSettings.getProperty("NonOwnerItemPickupDelayRaids", 285L) * 1000L;
		ALT_NO_LASTHIT = altSettings.getProperty("NoLasthitOnRaid", false);
		ALT_DISPEL_MUSIC = altSettings.getProperty("AltDispelDanceSong", false);
		ALT_KAMALOKA_NIGHTMARES_PREMIUM_ONLY = altSettings.getProperty("KamalokaNightmaresPremiumOnly", false);
		ALT_KAMALOKA_NIGHTMARE_REENTER = altSettings.getProperty("SellReenterNightmaresTicket", true);
		ALT_KAMALOKA_ABYSS_REENTER = altSettings.getProperty("SellReenterAbyssTicket", true);
		ALT_KAMALOKA_LAB_REENTER = altSettings.getProperty("SellReenterLabyrinthTicket", true);
		ALT_PET_HEAL_BATTLE_ONLY = altSettings.getProperty("PetsHealOnlyInBattle", true);
		CHAR_TITLE = altSettings.getProperty("CharTitle", false);
		ADD_CHAR_TITLE = altSettings.getProperty("CharAddTitle", "");
		ALT_ALLOW_SELL_COMMON = altSettings.getProperty("AllowSellCommon", true);
		ALT_ALLOW_SHADOW_WEAPONS = altSettings.getProperty("AllowShadowWeapons", true);
		ALT_DISABLED_MULTISELL = altSettings.getProperty("DisabledMultisells", ArrayUtils.EMPTY_INT_ARRAY);
		ALT_SHOP_PRICE_LIMITS = altSettings.getProperty("ShopPriceLimits", ArrayUtils.EMPTY_INT_ARRAY);
		ALT_SHOP_UNALLOWED_ITEMS = altSettings.getProperty("ShopUnallowedItems", ArrayUtils.EMPTY_INT_ARRAY);
		ALT_ALLOWED_PET_POTIONS = altSettings.getProperty("AllowedPetPotions", new int[]
			{
			735,
			1060,
			1061,
			1062,
			1374,
			1375,
			1539,
			1540,
			6035,
			6036
			});
		FESTIVAL_MIN_PARTY_SIZE = altSettings.getProperty("FestivalMinPartySize", 5);
		FESTIVAL_RATE_PRICE = altSettings.getProperty("FestivalRatePrice", 1.0);
		ENABLE_POLL_SYSTEM = altSettings.getProperty("EnablePoll", true);
		ANNOUNCE_POLL_EVERY_X_MIN = altSettings.getProperty("AnnounceToVoteInMin", 10);
		RIFT_MIN_PARTY_SIZE = altSettings.getProperty("RiftMinPartySize", 5);
		RIFT_SPAWN_DELAY = altSettings.getProperty("RiftSpawnDelay", 10000);
		RIFT_MAX_JUMPS = altSettings.getProperty("MaxRiftJumps", 4);
		RIFT_AUTO_JUMPS_TIME = altSettings.getProperty("AutoJumpsDelay", 8);
		RIFT_AUTO_JUMPS_TIME_RAND = altSettings.getProperty("AutoJumpsDelayRandom", 120000);
		RIFT_ENTER_COST_RECRUIT = altSettings.getProperty("RecruitFC", 18);
		RIFT_ENTER_COST_SOLDIER = altSettings.getProperty("SoldierFC", 21);
		RIFT_ENTER_COST_OFFICER = altSettings.getProperty("OfficerFC", 24);
		RIFT_ENTER_COST_CAPTAIN = altSettings.getProperty("CaptainFC", 27);
		RIFT_ENTER_COST_COMMANDER = altSettings.getProperty("CommanderFC", 30);
		RIFT_ENTER_COST_HERO = altSettings.getProperty("HeroFC", 33);
		ALLOW_CLANSKILLS = altSettings.getProperty("AllowClanSkills", true);
		ALLOW_LEARN_TRANS_SKILLS_WO_QUEST = altSettings.getProperty("AllowLearnTransSkillsWOQuest", false);
		PARTY_LEADER_ONLY_CAN_INVITE = altSettings.getProperty("PartyLeaderOnlyCanInvite", true);
		ALLOW_TALK_WHILE_SITTING = altSettings.getProperty("AllowTalkWhileSitting", true);
		ALLOW_NOBLE_TP_TO_ALL = altSettings.getProperty("AllowNobleTPToAll", false);
		CLANHALL_BUFFTIME_MODIFIER = altSettings.getProperty("ClanHallBuffTimeModifier", 1.0);
		SONGDANCETIME_MODIFIER = altSettings.getProperty("SongDanceTimeModifier", 1.0);
		MAXLOAD_MODIFIER = altSettings.getProperty("MaxLoadModifier", 1.0);
		GATEKEEPER_MODIFIER = altSettings.getProperty("GkCostMultiplier", 1.0);
		GATEKEEPER_FREE = altSettings.getProperty("GkFree", 40);
		CRUMA_GATEKEEPER_LVL = altSettings.getProperty("GkCruma", 65);
		ALT_IMPROVED_PETS_LIMITED_USE = altSettings.getProperty("ImprovedPetsLimitedUse", false);
		ALT_CHAMPION_CHANCE1 = altSettings.getProperty("AltChampionChance1", 0.);
		ALT_CHAMPION_CHANCE2 = altSettings.getProperty("AltChampionChance2", 0.);
		ALT_CHAMPION_CAN_BE_AGGRO = altSettings.getProperty("AltChampionAggro", false);
		ALT_CHAMPION_CAN_BE_SOCIAL = altSettings.getProperty("AltChampionSocial", false);
		ALT_CHAMPION_DROP_HERBS = altSettings.getProperty("AltChampionDropHerbs", false);
		ALT_SHOW_MONSTERS_AGRESSION = altSettings.getProperty("AltShowMonstersAgression", false);
		ALT_SHOW_MONSTERS_LVL = altSettings.getProperty("AltShowMonstersLvL", false);
		ALT_CHAMPION_TOP_LEVEL = altSettings.getProperty("AltChampionTopLevel", 75);
		ALT_CHAMPION_MIN_LEVEL = altSettings.getProperty("AltChampionMinLevel", 20);
		ALT_VITALITY_ENABLED = altSettings.getProperty("AltVitalityEnabled", true);
		ALT_VITALITY_RATE = altSettings.getProperty("AltVitalityRate", 1.);
		ALT_VITALITY_CONSUME_RATE = altSettings.getProperty("AltVitalityConsumeRate", 1.);
		ALT_VITALITY_RAID_BONUS = altSettings.getProperty("AltVitalityRaidBonus", 2000);
		ALT_PCBANG_POINTS_ENABLED = altSettings.getProperty("AltPcBangPointsEnabled", false);
		ALT_PCBANG_POINTS_BONUS_DOUBLE_CHANCE = altSettings.getProperty("AltPcBangPointsDoubleChance", 10.);
		ALT_PCBANG_POINTS_BONUS = altSettings.getProperty("AltPcBangPointsBonus", 0);
		ALT_PCBANG_POINTS_DELAY = altSettings.getProperty("AltPcBangPointsDelay", 20);
		ALT_PCBANG_POINTS_MIN_LVL = altSettings.getProperty("AltPcBangPointsMinLvl", 1);
		ALT_MAX_ALLY_SIZE = altSettings.getProperty("AltMaxAllySize", 3);
		ALT_PARTY_DISTRIBUTION_RANGE = altSettings.getProperty("AltPartyDistributionRange", 1500);
		ALT_PARTY_BONUS = altSettings.getProperty("AltPartyBonus", new double[]
			{
			1.00,
			1.10,
			1.20,
			1.30,
			1.40,
			1.50,
			2.00,
			2.10,
			2.20
			});
		ALT_LEVEL_DIFFERENCE_PROTECTION = altSettings.getProperty("LevelDifferenceProtection", -100);
		ALT_ALL_PHYS_SKILLS_OVERHIT = altSettings.getProperty("AltAllPhysSkillsOverhit", true);
		ALT_REMOVE_SKILLS_ON_DELEVEL = altSettings.getProperty("AltRemoveSkillsOnDelevel", true);
		ALT_USE_BOW_REUSE_MODIFIER = altSettings.getProperty("AltUseBowReuseModifier", true);
		ALLOW_CH_DOOR_OPEN_ON_CLICK = altSettings.getProperty("AllowChDoorOpenOnClick", true);
		ALT_CH_ALL_BUFFS = altSettings.getProperty("AltChAllBuffs", false);
		ALT_CH_ALLOW_1H_BUFFS = altSettings.getProperty("AltChAllowHourBuff", false);
		ALT_CH_SIMPLE_DIALOG = altSettings.getProperty("AltChSimpleDialog", false);
		AUGMENTATION_NG_SKILL_CHANCE = altSettings.getProperty("AugmentationNGSkillChance", 15);
		AUGMENTATION_NG_GLOW_CHANCE = altSettings.getProperty("AugmentationNGGlowChance", 0);
		AUGMENTATION_MID_SKILL_CHANCE = altSettings.getProperty("AugmentationMidSkillChance", 30);
		AUGMENTATION_MID_GLOW_CHANCE = altSettings.getProperty("AugmentationMidGlowChance", 40);
		AUGMENTATION_HIGH_SKILL_CHANCE = altSettings.getProperty("AugmentationHighSkillChance", 45);
		AUGMENTATION_HIGH_GLOW_CHANCE = altSettings.getProperty("AugmentationHighGlowChance", 70);
		AUGMENTATION_TOP_SKILL_CHANCE = altSettings.getProperty("AugmentationTopSkillChance", 60);
		AUGMENTATION_TOP_GLOW_CHANCE = altSettings.getProperty("AugmentationTopGlowChance", 100);
		AUGMENTATION_BASESTAT_CHANCE = altSettings.getProperty("AugmentationBaseStatChance", 1);
		AUGMENTATION_ACC_SKILL_CHANCE = altSettings.getProperty("AugmentationAccSkillChance", 10);
		ALT_OPEN_CLOAK_SLOT = altSettings.getProperty("OpenCloakSlot", false);
		FOLLOW_RANGE = altSettings.getProperty("FollowRange", 100);
		ALT_ENABLE_MULTI_PROFA = altSettings.getProperty("AltEnableMultiProfa", false);
		ALT_ITEM_AUCTION_ENABLED = altSettings.getProperty("AltItemAuctionEnabled", true);
		ALT_ITEM_AUCTION_CAN_REBID = altSettings.getProperty("AltItemAuctionCanRebid", false);
		ALT_ITEM_AUCTION_START_ANNOUNCE = altSettings.getProperty("AltItemAuctionAnnounce", true);
		ALT_ITEM_AUCTION_BID_ITEM_ID = altSettings.getProperty("AltItemAuctionBidItemId", 57);
		ALT_ITEM_AUCTION_MAX_BID = altSettings.getProperty("AltItemAuctionMaxBid", 1000000L);
		ALT_ITEM_AUCTION_MAX_CANCEL_TIME_IN_MILLIS = altSettings.getProperty("AltItemAuctionMaxCancelTimeInMillis", 604800000);
		ENABLE_AUCTION_SYSTEM = altSettings.getProperty("EnableAuctionSystem", true);
		AUCTION_FEE = Integer.parseInt(altSettings.getProperty("AuctionFee", "100000"));
		AUCTION_INACTIVITY_DAYS_TO_DELETE = Integer.parseInt(altSettings.getProperty("AuctionInactivityDaysToDelete", "7"));
		ALLOW_AUCTION_OUTSIDE_TOWN = altSettings.getProperty("AuctionOutsideTown", false);
		SECONDS_BETWEEN_ADDING_AUCTIONS = Integer.parseInt(altSettings.getProperty("AuctionAddDelay", "30"));
		AUCTION_PRIVATE_STORE_AUTO_ADDED = altSettings.getProperty("AuctionPrivateStoreAutoAdded", true);
		ALT_FISH_CHAMPIONSHIP_ENABLED = altSettings.getProperty("AltFishChampionshipEnabled", true);
		ALT_FISH_CHAMPIONSHIP_REWARD_ITEM = altSettings.getProperty("AltFishChampionshipRewardItemId", 57);
		ALT_FISH_CHAMPIONSHIP_REWARD_1 = altSettings.getProperty("AltFishChampionshipReward1", 800000);
		ALT_FISH_CHAMPIONSHIP_REWARD_2 = altSettings.getProperty("AltFishChampionshipReward2", 500000);
		ALT_FISH_CHAMPIONSHIP_REWARD_3 = altSettings.getProperty("AltFishChampionshipReward3", 300000);
		ALT_FISH_CHAMPIONSHIP_REWARD_4 = altSettings.getProperty("AltFishChampionshipReward4", 200000);
		ALT_FISH_CHAMPIONSHIP_REWARD_5 = altSettings.getProperty("AltFishChampionshipReward5", 100000);
		ALT_ENABLE_BLOCK_CHECKER_EVENT = altSettings.getProperty("EnableBlockCheckerEvent", true);
		ALT_MIN_BLOCK_CHECKER_TEAM_MEMBERS = Math.min(Math.max(altSettings.getProperty("BlockCheckerMinTeamMembers", 1), 1), 6);
		ALT_RATE_COINS_REWARD_BLOCK_CHECKER = altSettings.getProperty("BlockCheckerRateCoinReward", 1.);
		ALT_HBCE_FAIR_PLAY = altSettings.getProperty("HBCEFairPlay", false);
		ALT_PET_INVENTORY_LIMIT = altSettings.getProperty("AltPetInventoryLimit", 12);
		ALT_CLAN_LEVEL_CREATE = altSettings.getProperty("ClanLevelCreate", 0);
		CLAN_LEVEL_6_COST = altSettings.getProperty("ClanLevel6Cost", 5000);
		CLAN_LEVEL_7_COST = altSettings.getProperty("ClanLevel7Cost", 10000);
		CLAN_LEVEL_8_COST = altSettings.getProperty("ClanLevel8Cost", 20000);
		CLAN_LEVEL_9_COST = altSettings.getProperty("ClanLevel9Cost", 40000);
		CLAN_LEVEL_10_COST = altSettings.getProperty("ClanLevel10Cost", 40000);
		CLAN_LEVEL_11_COST = altSettings.getProperty("ClanLevel11Cost", 75000);
		CLAN_LEVEL_6_REQUIREMEN = altSettings.getProperty("ClanLevel6Requirement", 30);
		CLAN_LEVEL_7_REQUIREMEN = altSettings.getProperty("ClanLevel7Requirement", 50);
		CLAN_LEVEL_8_REQUIREMEN = altSettings.getProperty("ClanLevel8Requirement", 80);
		CLAN_LEVEL_9_REQUIREMEN = altSettings.getProperty("ClanLevel9Requirement", 120);
		CLAN_LEVEL_10_REQUIREMEN = altSettings.getProperty("ClanLevel10Requirement", 140);
		CLAN_LEVEL_11_REQUIREMEN = altSettings.getProperty("ClanLevel11Requirement", 170);
		BLOOD_OATHS = altSettings.getProperty("BloodOaths", 150);
		BLOOD_PLEDGES = altSettings.getProperty("BloodPledges", 5);
		MIN_ACADEM_POINT = altSettings.getProperty("MinAcademPoint", 190);
		MAX_ACADEM_POINT = altSettings.getProperty("MaxAcademPoint", 650);
		HELLBOUND_LEVEL = altSettings.getProperty("HellboundLevel", 0);
		CLAN_LEAVE_PENALTY = altSettings.getProperty("ClanLeavePenalty", 24);
		ALLY_LEAVE_PENALTY = altSettings.getProperty("AllyLeavePenalty", 24);
		DISSOLVED_ALLY_PENALTY = altSettings.getProperty("DissolveAllyPenalty", 24);
		DISSOLVED_CLAN_PENALTY = altSettings.getProperty("DissolvedClanPenalty", 24);
		CLAN_DISBAND_TIME = altSettings.getProperty("DisbanClanTime", 48);
		SIEGE_PVP_COUNT = altSettings.getProperty("SiegePvpCount", false);
		ZONE_PVP_COUNT = altSettings.getProperty("ZonePvpCount", false);
		EPIC_EXPERTISE_PENALTY = altSettings.getProperty("EpicExpertisePenalty", true);
		EXPERTISE_PENALTY = altSettings.getProperty("ExpertisePenalty", true);
		ALT_MUSIC_LIMIT = altSettings.getProperty("MusicLimit", 12);
		ALT_DEBUFF_LIMIT = altSettings.getProperty("DebuffLimit", 8);
		ALT_TRIGGER_LIMIT = altSettings.getProperty("TriggerLimit", 12);
		ENABLE_MODIFY_SKILL_DURATION = altSettings.getProperty("EnableSkillDuration", false);
		if (ENABLE_MODIFY_SKILL_DURATION)
		{
			String[] propertySplit = altSettings.getProperty("SkillDurationList", "").split(";");
			SKILL_DURATION_LIST = new TIntIntHashMap(propertySplit.length);
			for (String skill : propertySplit)
			{
				String[] skillSplit = skill.split(",");
				if (skillSplit.length != 2)
				{
					_log.warn("[SkillDurationList]: invalid config property -> SkillDurationList \"" + skill + "\"");
				}
				else
				{
					try
					{
						SKILL_DURATION_LIST.put(Integer.parseInt(skillSplit[0]), Integer.parseInt(skillSplit[1]));
					}
					catch (NumberFormatException nfe)
					{
						if (!skill.isEmpty())
						{
							_log.warn("[SkillDurationList]: invalid config property -> SkillList \"" + skillSplit[0] + "\"" + skillSplit[1]);
						}
					}
				}
			}
		}
		ALT_TIME_MODE_SKILL_DURATION = altSettings.getProperty("AltTimeModeSkillDuration", false);
		ANCIENT_HERB_SPAWN_RADIUS = altSettings.getProperty("AncientHerbSpawnRadius", 600);
		ANCIENT_HERB_SPAWN_CHANCE = altSettings.getProperty("AncientHerbSpawnChance", 3);
		ANCIENT_HERB_SPAWN_COUNT = altSettings.getProperty("AncientHerbSpawnCount", 5);
		ANCIENT_HERB_RESPAWN_TIME = altSettings.getProperty("AncientHerbRespawnTime", 60) * 1000;
		ANCIENT_HERB_DESPAWN_TIME = altSettings.getProperty("AncientHerbDespawnTime", 60) * 1000;
		String[] locs = altSettings.getProperty("AncientHerbSpawnPoints", "").split(";");
		if (locs != null)
		{
			for (String string : locs)
			{
				if (string != null)
				{
					String[] cords = string.split(",");
					int x = Integer.parseInt(cords[0]);
					int y = Integer.parseInt(cords[1]);
					int z = Integer.parseInt(cords[2]);
					HEIN_FIELDS_LOCATIONS.add(new Location(x, y, z));
				}
			}
		}
	}

	public static void loadPvPmodConfig()
	{
		ExProperties PvPmodConfig = load(PVP_MOD_CONFIG_FILE);

		CONSUMABLE_SHOT = PvPmodConfig.getProperty("ConsumableShot", true);
		CONSUMABLE_ARROW = PvPmodConfig.getProperty("ConsumableArrow", true);
		ATT_MOD_ARMOR = PvPmodConfig.getProperty("att_mod_Armor", 6);
		ATT_MOD_WEAPON = PvPmodConfig.getProperty("att_mod_Weapon", 5);
		ATT_MOD_WEAPON1 = PvPmodConfig.getProperty("att_mod_Weapon1", 20);
		ATT_MOD_MAX_ARMOR = PvPmodConfig.getProperty("att_mod_max_armor", 60);
		ATT_MOD_MAX_WEAPON = PvPmodConfig.getProperty("att_mod_max_weapon", 150);
		HENNA_STATS = PvPmodConfig.getProperty("HennaStats", 5);
		ENEBLE_TITLE_COLOR_MOD = PvPmodConfig.getProperty("EnebleTitleColorMod", false);
		TYPE_TITLE_COLOR_MOD = PvPmodConfig.getProperty("TypeTitleColorMod", "PvP");
		COUNT_TITLE_1 = PvPmodConfig.getProperty("CountTitle_1", 50);
		TITLE_COLOR_1 = Integer.decode("0x" + PvPmodConfig.getProperty("TitleColor_1", "FFFFFF"));
		COUNT_TITLE_2 = PvPmodConfig.getProperty("CountTitle_2", 100);
		TITLE_COLOR_2 = Integer.decode("0x" + PvPmodConfig.getProperty("TitleColor_2", "FFFFFF"));
		COUNT_TITLE_3 = PvPmodConfig.getProperty("CountTitle_3", 250);
		TITLE_COLOR_3 = Integer.decode("0x" + PvPmodConfig.getProperty("TitleColor_3", "FFFFFF"));
		COUNT_TITLE_4 = PvPmodConfig.getProperty("CountTitle_4", 500);
		TITLE_COLOR_4 = Integer.decode("0x" + PvPmodConfig.getProperty("TitleColor_4", "FFFFFF"));
		COUNT_TITLE_5 = PvPmodConfig.getProperty("CountTitle_5", 1000);
		TITLE_COLOR_5 = Integer.decode("0x" + PvPmodConfig.getProperty("TitleColor_5", "FFFFFF"));
		ENEBLE_NAME_COLOR_MOD = PvPmodConfig.getProperty("EnebleNameColorMod", false);
		TYPE_NAME_COLOR_MOD = PvPmodConfig.getProperty("TypeNameColorMod", "Pk");
		COUNT_NAME_1 = PvPmodConfig.getProperty("CountName_1", 50);
		NAME_COLOR_1 = Integer.decode("0x" + PvPmodConfig.getProperty("NameColor_1", "FFFFFF"));
		COUNT_NAME_2 = PvPmodConfig.getProperty("CountName_2", 100);
		NAME_COLOR_2 = Integer.decode("0x" + PvPmodConfig.getProperty("NameColor_2", "FFFFFF"));
		COUNT_NAME_3 = PvPmodConfig.getProperty("CountName_3", 250);
		NAME_COLOR_3 = Integer.decode("0x" + PvPmodConfig.getProperty("NameColor_3", "FFFFFF"));
		COUNT_NAME_4 = PvPmodConfig.getProperty("CountName_4", 500);
		NAME_COLOR_4 = Integer.decode("0x" + PvPmodConfig.getProperty("NameColor_4", "FFFFFF"));
		COUNT_NAME_5 = PvPmodConfig.getProperty("CountName_5", 1000);
		NAME_COLOR_5 = Integer.decode("0x" + PvPmodConfig.getProperty("NameColor_5", "FFFFFF"));
		NEW_CHAR_IS_NOBLE = PvPmodConfig.getProperty("NewCharIsNoble", false);
		NEW_CHAR_IS_HERO = PvPmodConfig.getProperty("NewCharIsHero", false);
		ANNOUNCE_SPAWN_RB = PvPmodConfig.getProperty("AnnounceToSpawnRb", false);
		SPAWN_CHAR = PvPmodConfig.getProperty("CustomSpawn", false);
		SPAWN_X = PvPmodConfig.getProperty("SpawnX", 1);
		SPAWN_Y = PvPmodConfig.getProperty("SpawnY", 1);
		SPAWN_Z = PvPmodConfig.getProperty("SpawnZ", 1);
		ADEPT_ENABLE = PvPmodConfig.getProperty("ADEPT_ENABLE", true);
		SPAWN_CITIES_TREE = PvPmodConfig.getProperty("SPAWN_CITIES_TREE", true);
		SPAWN_NPC_BUFFER = PvPmodConfig.getProperty("SPAWN_NPC_BUFFER", true);
		SPAWN_scrubwoman = PvPmodConfig.getProperty("SPAWN_scrubwoman", true);
		MAX_PARTY_SIZE = PvPmodConfig.getProperty("MaxPartySize", 9);
		KILLING_SPREE_ENABLED = PvPmodConfig.getProperty("KillingSpreeEnabled", false);
		KILLING_SPREE_ANNOUNCEMENTS = new HashMap<Integer, String>();
		String[] split = PvPmodConfig.getProperty("KillingSpreeAnnouncements", "").split(";");
		if (!split[0].isEmpty())
		{
			for (String ps : split)
			{
				final String[] pvp = ps.split(",");
				if (pvp.length != 2)
					_log.error("[KillingSpreeAnnouncements]: invalid config property -> KillingSpree \"" + ps + "\"");
				else
					try
					{
						KILLING_SPREE_ANNOUNCEMENTS.put(Integer.parseInt(pvp[0]), pvp[1]);
					}
					catch (final NumberFormatException nfe)
					{
						nfe.printStackTrace();
						if (!ps.equals(""))
							_log.error("[KillingSpreeAnnouncements]: invalid config property -> KillingSpree \"" + Integer.parseInt(pvp[0]) + "\"" + pvp[1]);
					}
			}
		}
		KILLING_SPREE_COLORS = new HashMap<Integer, String>();
		split = PvPmodConfig.getProperty("KillingSpreeColors", "").split(";");
		if (!split[0].isEmpty())
		{
			for (String ps : split)
			{
				final String[] pvp = ps.split(",");
				if (pvp.length != 2)
					_log.error("[KillingSpreeColors]: invalid config property -> KillingSpree \"" + ps + "\"");
				else
					try
					{
						KILLING_SPREE_COLORS.put(Integer.parseInt(pvp[0]), pvp[1]);
					}
					catch (final NumberFormatException nfe)
					{
						nfe.printStackTrace();
						if (!ps.equals(""))
							_log.error("[KillingSpreeColors]: invalid config property -> KillingSpree \"" + Integer.parseInt(pvp[0]) + "\"" + pvp[1]);
					}
			}
		}
	}

	public static int _coinID;
	public static boolean ALLOW_UPDATE_ANNOUNCER;

	public static void loadServicesSettings()
	{
		ExProperties servicesSettings = load(SERVICES_FILE);

		_coinID = servicesSettings.getProperty("Id_Item_Mall", 57);
		ENTER_WORLD_ANNOUNCEMENTS_HERO_LOGIN = servicesSettings.getProperty("AnnounceHero", false);
		ENTER_WORLD_ANNOUNCEMENTS_LORD_LOGIN = servicesSettings.getProperty("AnnounceLord", false);
		SERVICES_DELEVEL_ENABLED = servicesSettings.getProperty("AllowDelevel", false);
		SERVICES_DELEVEL_ITEM = servicesSettings.getProperty("DelevelItem", 57);
		SERVICES_DELEVEL_COUNT = servicesSettings.getProperty("DelevelCount", 1000);
		SERVICES_DELEVEL_MIN_LEVEL = servicesSettings.getProperty("DelevelMinLevel", 1);
		ALLOW_MAIL_OPTION = servicesSettings.getProperty("AllowMailOption", false);
		for (int id : servicesSettings.getProperty("AllowClassMasters", ArrayUtils.EMPTY_INT_ARRAY))
		{
			if (id != 0)
			{
				ALLOW_CLASS_MASTERS_LIST.add(id);
			}
		}
		CLASS_MASTERS_PRICE = servicesSettings.getProperty("ClassMastersPrice", "0,0,0");
		if (CLASS_MASTERS_PRICE.length() >= 5)
		{
			int level = 1;
			for (String id : CLASS_MASTERS_PRICE.split(","))
			{
				CLASS_MASTERS_PRICE_LIST[level] = Integer.parseInt(id);
				level++;
			}
		}
		SERVICES_RIDE_HIRE_ENABLED = servicesSettings.getProperty("RideHireEnabled", false);
		CLASS_MASTERS_PRICE_ITEM = servicesSettings.getProperty("ClassMastersPriceItem", 57);
		SERVICES_CHANGE_NICK_ALLOW_SYMBOL2 = servicesSettings.getProperty("NickChangeAllowSimbol2", false);
		SERVICES_CHANGE_NICK_ENABLED2 = servicesSettings.getProperty("NickChangeEnabled2", false);
		SERVICES_CHANGE_NICK_PRICE2 = servicesSettings.getProperty("NickChangePrice2", 100);
		SERVICES_CHANGE_NICK_ITEM2 = servicesSettings.getProperty("NickChangeItem2", 37000);
		SERVICES_CHANGE_CLAN_NAME_ENABLED2 = servicesSettings.getProperty("ClanNameChangeEnabled2", false);
		SERVICES_CHANGE_CLAN_NAME_PRICE2 = servicesSettings.getProperty("ClanNameChangePrice2", 100);
		SERVICES_CHANGE_CLAN_NAME_ITEM2 = servicesSettings.getProperty("ClanNameChangeItem2", 4037);
		SERVICES_CHANGE_PET_NAME_ENABLED = servicesSettings.getProperty("PetNameChangeEnabled", false);
		SERVICES_CHANGE_PET_NAME_PRICE = servicesSettings.getProperty("PetNameChangePrice", 100);
		SERVICES_CHANGE_PET_NAME_ITEM = servicesSettings.getProperty("PetNameChangeItem", 4037);
		SERVICES_EXCHANGE_BABY_PET_ENABLED = servicesSettings.getProperty("BabyPetExchangeEnabled", false);
		SERVICES_EXCHANGE_BABY_PET_PRICE = servicesSettings.getProperty("BabyPetExchangePrice", 100);
		SERVICES_EXCHANGE_BABY_PET_ITEM = servicesSettings.getProperty("BabyPetExchangeItem", 4037);
		SERVICES_CHANGE_SEX_ENABLED = servicesSettings.getProperty("SexChangeEnabled", false);
		SERVICES_CHANGE_SEX_PRICE = servicesSettings.getProperty("SexChangePrice", 100);
		SERVICES_CHANGE_SEX_ITEM = servicesSettings.getProperty("SexChangeItem", 4037);
		SERVICES_CHANGE_BASE_ENABLED = servicesSettings.getProperty("BaseChangeEnabled", false);
		SERVICES_CHANGE_BASE_PRICE = servicesSettings.getProperty("BaseChangePrice", 100);
		SERVICES_CHANGE_BASE_ITEM = servicesSettings.getProperty("BaseChangeItem", 4037);
		SERVICES_SEPARATE_SUB_ENABLED = servicesSettings.getProperty("SeparateSubEnabled", false);
		SERVICES_SEPARATE_SUB_PRICE = servicesSettings.getProperty("SeparateSubPrice", 100);
		SERVICES_SEPARATE_SUB_ITEM = servicesSettings.getProperty("SeparateSubItem", 4037);
		SERVICES_CHANGE_NICK_COLOR_ENABLED = servicesSettings.getProperty("NickColorChangeEnabled", false);
		SERVICES_CHANGE_NICK_COLOR_PRICE = servicesSettings.getProperty("NickColorChangePrice", 100);
		SERVICES_CHANGE_NICK_COLOR_ITEM = servicesSettings.getProperty("NickColorChangeItem", 4037);
		SERVICES_CHANGE_NICK_COLOR_LIST = servicesSettings.getProperty("NickColorChangeList", new String[] { "00FF00" });
		SERVICES_CHANGE_Title_COLOR_ENABLED = servicesSettings.getProperty("TitleColorChangeEnabled", false);
		SERVICES_CHANGE_Title_COLOR_PRICE = servicesSettings.getProperty("TitleColorChangePrice", 100);
		SERVICES_CHANGE_Title_COLOR_ITEM = servicesSettings.getProperty("TitleColorChangeItem", 4037);
		SERVICES_CHANGE_Title_COLOR_LIST = servicesSettings.getProperty("TitleColorChangeList", new String[] { "00FF00" });
		SERVICES_BASH_ENABLED = servicesSettings.getProperty("BashEnabled", false);
		SERVICES_BASH_SKIP_DOWNLOAD = servicesSettings.getProperty("BashSkipDownload", false);
		SERVICES_BASH_RELOAD_TIME = servicesSettings.getProperty("BashReloadTime", 24);
		SERVICES_HERO_SELL_ENABLED = servicesSettings.getProperty("HeroSellEnabled", false);
		SERVICES_HERO_SELL_DAY = servicesSettings.getProperty("HeroSellDay", new int[] { 30 });
		SERVICES_HERO_SELL_PRICE = servicesSettings.getProperty("HeroSellPrice", new int[] { 30 });
		SERVICES_HERO_SELL_ITEM = servicesSettings.getProperty("HeroSellItem", new int[] { 4037 });
		SERVICES_HERO_SELL_CHAT = servicesSettings.getProperty("HeroChat", false);
		SERVICES_HERO_SELL_SKILL = servicesSettings.getProperty("HeroSkills", false);
		SERVICES_HERO_SELL_ITEMS = servicesSettings.getProperty("HeroItems", false);
		SERVICES_CLAN_PROMOTION_ENABLE = servicesSettings.getProperty("EnableClanPromotion", false);
		SERVICES_CLAN_PROMOTION_MAX_LEVEL = servicesSettings.getProperty("MaxClanLevel", 6);
		SERVICES_CLAN_PROMOTION_MIN_ONLINE = servicesSettings.getProperty("MinOnlineMembers", 10);
		SERVICES_CLAN_PROMOTION_ITEM = servicesSettings.getProperty("ClanPromotionItemId", 57);
		SERVICES_CLAN_PROMOTION_ITEM_COUNT = servicesSettings.getProperty("ClanPromotionItemCOunt", 1000);
		SERVICES_CLAN_PROMOTION_SET_LEVEL = servicesSettings.getProperty("ClanPromotionSetLevel", 5);
		SERVICES_CLAN_PROMOTION_ADD_REP = servicesSettings.getProperty("ClanPromotionAddrep", 0);
		SERVICE_CLAN_PRMOTION_ADD_EGGS = servicesSettings.getProperty("GiveEggsToNewClans", false);
		CLAN_PROMOTION_CLAN_EGGS = servicesSettings.getProperty("ClanEggsToReward", "").replaceAll(" ", "").split(";");
		SERVICES_WASH_PK_ENABLED = servicesSettings.getProperty("WashPkEnabled", false);
		SERVICES_WASH_PK_ITEM = servicesSettings.getProperty("WashPkItem", 4037);
		SERVICES_WASH_PK_PRICE = servicesSettings.getProperty("WashPkPrice", 5);
		SERVICES_WASH_KARMA_ITEM = servicesSettings.getProperty("WashKarmaItem", 4037);
		SERVICES_WASH_KARMA_PRICE = servicesSettings.getProperty("WashKarmaPrice", 5);
		SERVICES_CLEAR_PK_PRICE = servicesSettings.getProperty("ClearPkPrice", 10000);
		SERVICES_CLEAR_PK_PRICE_ITEM_ID = servicesSettings.getProperty("ClearPkPriceID", 57);
		SERVICES_CLEAR_PK_COUNT = servicesSettings.getProperty("ClearPkCount", 1);
		SERVICES_TRANSFER_WEAPON_ENABLED = servicesSettings.getProperty("TransferWeaponEnabled", false);
		SERVICES_TRANSFER_WEAPON_ITEM = servicesSettings.getProperty("TransferWeaponPriceID", 4037);
		SERVICES_TRANSFER_WEAPON_PRICE = servicesSettings.getProperty("TransferWeaponPriceCount", 5);
		SERVICES_EXPAND_INVENTORY_ENABLED = servicesSettings.getProperty("ExpandInventoryEnabled", false);
		SERVICES_EXPAND_INVENTORY_PRICE = servicesSettings.getProperty("ExpandInventoryPrice", 1000);
		SERVICES_EXPAND_INVENTORY_ITEM = servicesSettings.getProperty("ExpandInventoryItem", 4037);
		SERVICES_EXPAND_INVENTORY_MAX = servicesSettings.getProperty("ExpandInventoryMax", 250);
		SERVICES_EXPAND_WAREHOUSE_ENABLED = servicesSettings.getProperty("ExpandWarehouseEnabled", false);
		SERVICES_EXPAND_WAREHOUSE_PRICE = servicesSettings.getProperty("ExpandWarehousePrice", 1000);
		SERVICES_EXPAND_WAREHOUSE_ITEM = servicesSettings.getProperty("ExpandWarehouseItem", 4037);
		SERVICES_EXPAND_CWH_ENABLED = servicesSettings.getProperty("ExpandCWHEnabled", false);
		SERVICES_EXPAND_CWH_PRICE = servicesSettings.getProperty("ExpandCWHPrice", 1000);
		SERVICES_EXPAND_CWH_ITEM = servicesSettings.getProperty("ExpandCWHItem", 4037);
		SERVICES_SELLPETS = servicesSettings.getProperty("SellPets", "");
		SERVICES_OFFLINE_TRADE_ALLOW = servicesSettings.getProperty("AllowOfflineTrade", false);
		SERVICES_OFFLINE_TRADE_ALLOW_OFFSHORE = servicesSettings.getProperty("AllowOfflineTradeOnlyOffshore", true);
		SERVICES_OFFLINE_TRADE_MIN_LEVEL = servicesSettings.getProperty("OfflineMinLevel", 0);
		SERVICES_OFFLINE_TRADE_NAME_COLOR = Integer.decode("0x" + servicesSettings.getProperty("OfflineTradeNameColor", "B0FFFF"));
		SERVICES_OFFLINE_TRADE_PRICE_ITEM = servicesSettings.getProperty("OfflineTradePriceItem", 0);
		SERVICES_OFFLINE_TRADE_PRICE = servicesSettings.getProperty("OfflineTradePrice", 0);
		SERVICES_OFFLINE_TRADE_SECONDS_TO_KICK = servicesSettings.getProperty("OfflineTradeDaysToKick", 14) * 86400L;
		SERVICES_OFFLINE_TRADE_RESTORE_AFTER_RESTART = servicesSettings.getProperty("OfflineRestoreAfterRestart", true);
		SERVICES_NO_TRADE_ONLY_OFFLINE = servicesSettings.getProperty("NoTradeOnlyOffline", false);
		SERVICES_NO_TRADE_BLOCK_ZONE = servicesSettings.getProperty("NoTradeBlockZone", false);
		SERVICES_TRADE_TAX = servicesSettings.getProperty("TradeTax", 0.0);
		SERVICES_OFFSHORE_TRADE_TAX = servicesSettings.getProperty("OffshoreTradeTax", 0.0);
		SERVICES_TRADE_TAX_ONLY_OFFLINE = servicesSettings.getProperty("TradeTaxOnlyOffline", false);
		SERVICES_OFFSHORE_NO_CASTLE_TAX = servicesSettings.getProperty("NoCastleTaxInOffshore", false);
		SERVICES_TRADE_ONLY_FAR = servicesSettings.getProperty("TradeOnlyFar", false);
		SERVICES_TRADE_MIN_LEVEL = servicesSettings.getProperty("MinLevelForTrade", 0);
		SERVICES_TRADE_RADIUS = servicesSettings.getProperty("TradeRadius", 30);
		SERVICES_GIRAN_HARBOR_ENABLED = servicesSettings.getProperty("GiranHarborZone", false);
		SERVICES_PARNASSUS_ENABLED = servicesSettings.getProperty("ParnassusZone", false);
		SERVICES_PARNASSUS_NOTAX = servicesSettings.getProperty("ParnassusNoTax", false);
		SERVICES_PARNASSUS_PRICE = servicesSettings.getProperty("ParnassusPrice", 500000);
		SERVICES_ALLOW_LOTTERY = servicesSettings.getProperty("AllowLottery", false);
		SERVICES_LOTTERY_PRIZE = servicesSettings.getProperty("LotteryPrize", 50000);
		SERVICES_ALT_LOTTERY_PRICE = servicesSettings.getProperty("AltLotteryPrice", 2000);
		SERVICES_LOTTERY_TICKET_PRICE = servicesSettings.getProperty("LotteryTicketPrice", 2000);
		SERVICES_LOTTERY_5_NUMBER_RATE = servicesSettings.getProperty("Lottery5NumberRate", 0.6);
		SERVICES_LOTTERY_4_NUMBER_RATE = servicesSettings.getProperty("Lottery4NumberRate", 0.4);
		SERVICES_LOTTERY_3_NUMBER_RATE = servicesSettings.getProperty("Lottery3NumberRate", 0.2);
		SERVICES_LOTTERY_2_AND_1_NUMBER_PRIZE = servicesSettings.getProperty("Lottery2and1NumberPrize", 200);
		SERVICES_ALLOW_ROULETTE = servicesSettings.getProperty("AllowRoulette", false);
		SERVICES_ROULETTE_MIN_BET = servicesSettings.getProperty("RouletteMinBet", 1L);
		SERVICES_ROULETTE_MAX_BET = servicesSettings.getProperty("RouletteMaxBet", Long.MAX_VALUE);
		SERVICES_ENABLE_NO_CARRIER = servicesSettings.getProperty("EnableNoCarrier", false);
		SERVICES_NO_CARRIER_MIN_TIME = servicesSettings.getProperty("NoCarrierMinTime", 0);
		SERVICES_NO_CARRIER_MAX_TIME = servicesSettings.getProperty("NoCarrierMaxTime", 90);
		SERVICES_NO_CARRIER_DEFAULT_TIME = servicesSettings.getProperty("NoCarrierDefaultTime", 60);
		SERVICES_PK_PVP_KILL_ENABLE = servicesSettings.getProperty("PkPvPKillEnable", false);
		SERVICES_PVP_KILL_REWARD_ITEM = servicesSettings.getProperty("PvPkillRewardItem", 4037);
		SERVICES_PVP_KILL_REWARD_COUNT = servicesSettings.getProperty("PvPKillRewardCount", 1L);
		SERVICES_PK_KILL_REWARD_ITEM = servicesSettings.getProperty("PkkillRewardItem", 4037);
		SERVICES_PK_KILL_REWARD_COUNT = servicesSettings.getProperty("PkKillRewardCount", 1L);
		SERVICES_PK_PVP_TIE_IF_SAME_IP = servicesSettings.getProperty("PkPvPTieifSameIP", true);
		ALLOW_AWAY_STATUS = servicesSettings.getProperty("AllowAwayStatus", false);
		AWAY_ONLY_FOR_PREMIUM = servicesSettings.getProperty("AwayOnlyForPremium", true);
		AWAY_PLAYER_TAKE_AGGRO = servicesSettings.getProperty("AwayPlayerTakeAggro", false);
		AWAY_TITLE_COLOR = Integer.decode("0x" + servicesSettings.getProperty("AwayTitleColor", "0000FF")).intValue();
		AWAY_TIMER = servicesSettings.getProperty("AwayTimer", 30);
		BACK_TIMER = servicesSettings.getProperty("BackTimer", 30);
		AWAY_PEACE_ZONE = servicesSettings.getProperty("AwayOnlyInPeaceZone", false);
		SERVICES_ANNOUNCE_PK_ENABLED = servicesSettings.getProperty("AnnouncePK", false);
		SERVICES_ANNOUNCE_PVP_ENABLED = servicesSettings.getProperty("AnnouncePvP", false);
		ITEM_BROKER_ITEM_SEARCH = servicesSettings.getProperty("UseItemBrokerItemSearch", false);
		SERVICES_CHANGE_PASSWORD = servicesSettings.getProperty("ChangePassword", false);
		PASSWORD_PAY_ID = servicesSettings.getProperty("ChangePasswordPayId", 0);
		PASSWORD_PAY_COUNT = servicesSettings.getProperty("ChangePassowrdPayCount", 0);
		APASSWD_TEMPLATE = servicesSettings.getProperty("ApasswdTemplate", "[A-Za-z0-9]{5,16}");
		ALLOW_EVENT_GATEKEEPER = servicesSettings.getProperty("AllowEventGatekeeper", false);
		SERVICES_LVL_ENABLED = servicesSettings.getProperty("LevelChangeEnabled", false);
		SERVICES_LVL_UP_MAX = servicesSettings.getProperty("LevelUPChangeMax", 85);
		SERVICES_LVL_UP_PRICE = servicesSettings.getProperty("LevelUPChangePrice", 1000);
		SERVICES_LVL_UP_ITEM = servicesSettings.getProperty("LevelUPChangeItem", 4037);
		SERVICES_LVL_DOWN_MAX = servicesSettings.getProperty("LevelDownChangeMax", 1);
		SERVICES_LVL_DOWN_PRICE = servicesSettings.getProperty("LevelDownChangePrice", 1000);
		SERVICES_LVL_DOWN_ITEM = servicesSettings.getProperty("LevelDownChangeItem", 4037);
		ALLOW_MULTILANG_GATEKEEPER = servicesSettings.getProperty("AllowMultiLangGatekeeper", false);
		DEFAULT_GK_LANG = servicesSettings.getProperty("DefaultGKLang", "en");
		ALLOW_UPDATE_ANNOUNCER = servicesSettings.getProperty("AllowUpdateAnnouncer", false);
		ALLOW_FAKE_PLAYERS = servicesSettings.getProperty("AllowFakePlayers", false);
		FAKE_PLAYERS_SIT = servicesSettings.getProperty("FakePlayersSit", false);
		FAKE_PLAYERS_PERCENT = servicesSettings.getProperty("FakePlayersPercent", 100);
		ALLOW_ONLINE_PARSE = servicesSettings.getProperty("AllowParsTotalOnline", false);
		FIRST_UPDATE = servicesSettings.getProperty("FirstOnlineUpdate", 1);
		DELAY_UPDATE = servicesSettings.getProperty("OnlineUpdate", 5);
		SERVICES_HAIR_CHANGE_ITEM_ID = servicesSettings.getProperty("HairChangeItemID", 4037);
		SERVICES_HAIR_CHANGE_COUNT = servicesSettings.getProperty("HairChangeItemCount", 10);
		SERVICES_CLEAR_INSTANCE_PRICE_ID = servicesSettings.getProperty("ClearInstancePriceID", 57);
		SERVICES_CLEAR_INSTANCE_PRICE_COUNT = servicesSettings.getProperty("ClearInstancePriceCount", 1);
	}

	public static boolean NOT_USE_USER_VOICED;
	public static boolean ALLOW_TOTAL_ONLINE;
	public static boolean show_rates;

	public static void loadCommandssettings()
	{
		ExProperties CommandsSettings = load(COMMANDS_CONFIG_FILE);

		show_rates = CommandsSettings.getProperty("show_rates", false);
		NOT_USE_USER_VOICED = CommandsSettings.getProperty("NotUsePlayerVoiced", false);
		ENABLE_KM_ALL_TO_ME = CommandsSettings.getProperty("EnableKmAllToMe", false);
		COMMAND_RES = CommandsSettings.getProperty("Command_ress", false);
		ITEM_ID_RESS = CommandsSettings.getProperty("Item_Id_ress", 57);
		PRICE_RESS = CommandsSettings.getProperty("price_ress", 57);
		SERVICES_LOCK_ACCOUNT_IP = CommandsSettings.getProperty("LockAccountIpService", false);
		ALLOW_TOTAL_ONLINE = CommandsSettings.getProperty("AllowVoiceCommandOnline", false);
		COMMAND_FARM = CommandsSettings.getProperty("COMMAND_FARM", false);
		FARM_TELEPORT_ITEM_ID = CommandsSettings.getProperty("FARM_TELEPORT_ITEM_ID", 57);
		PRICE_FARM = CommandsSettings.getProperty("PRICE_FARM", 57);
		FARM_X = CommandsSettings.getProperty("FARM_X", 57);
		FARM_Y = CommandsSettings.getProperty("FARM_Y", 57);
		FARM_Z = CommandsSettings.getProperty("FARM_Z", 57);
		COMMAND_FARM_HARD = CommandsSettings.getProperty("COMMAND_FARM_HARD", false);
		FARM_HARD_TELEPORT_ITEM_ID = CommandsSettings.getProperty("FARM_HARD_TELEPORT_ITEM_ID", 57);
		PRICE_FARM_HARD = CommandsSettings.getProperty("PRICE_FARM_HARD", 57);
		FARM_HARD_X = CommandsSettings.getProperty("FARM_HARD_X", 57);
		FARM_HARD_Y = CommandsSettings.getProperty("FARM_HARD_Y", 57);
		FARM_HARD_Z = CommandsSettings.getProperty("FARM_HARD_Z", 57);
		COMMAND_FARM_LOW = CommandsSettings.getProperty("COMMAND_FARM_LOW", false);
		FARM_LOW_TELEPORT_ITEM_ID = CommandsSettings.getProperty("FARM_LOW_TELEPORT_ITEM_ID", 57);
		PRICE_FARM_LOW = CommandsSettings.getProperty("PRICE_FARM_LOW", 57);
		FARM_LOW_X = CommandsSettings.getProperty("FARM_LOW_X", 57);
		FARM_LOW_Y = CommandsSettings.getProperty("FARM_LOW_Y", 57);
		FARM_LOW_Z = CommandsSettings.getProperty("FARM_LOW_Z", 57);
		COMMAND_PVP = CommandsSettings.getProperty("COMMAND_PVP", false);
		PVP_X = CommandsSettings.getProperty("PVP_X", 0);
		PVP_Y = CommandsSettings.getProperty("PVP_Y", 0);
		PVP_Z = CommandsSettings.getProperty("PVP_Z", 0);
		PVP_TELEPORT_ITEM_ID = CommandsSettings.getProperty("PVP_TELEPORT_ITEM_ID", 57);
		PRICE_PVP = CommandsSettings.getProperty("PRICE_PVP", 57);
		ALLOW_VOICED_COMMANDS = CommandsSettings.getProperty("AllowVoicedCommands", true);
		ALT_SHOW_SERVER_TIME = CommandsSettings.getProperty("ShowServerTime", false);
		COMMAND_DRESSME_ENABLE = CommandsSettings.getProperty("DressMe", true);
	}

	public static void loadCommunityPvPboardsettings()
	{
		ExProperties CommunityPvPboardSettings = load(BOARD_MANAGER_CONFIG_FILE);

		BBS_PVP_CB_ENABLED = CommunityPvPboardSettings.getProperty("BBSPVPEnabled", false);
		COMMUNITYBOARD_ENABLED = CommunityPvPboardSettings.getProperty("AllowCommunityBoard", true);
		CUSTOM_X_CB = CommunityPvPboardSettings.getProperty("CUSTOM_X_CB", false);
		BBS_DEFAULT = CommunityPvPboardSettings.getProperty("BBSDefault", "_bbshome");
		BBS_HOME_DIR = CommunityPvPboardSettings.getProperty("BBSHomeDir", "scripts/services/community/");
		ALLOW_BBS_WAREHOUSE = CommunityPvPboardSettings.getProperty("AllowBBSWarehouse", true);
		BBS_WAREHOUSE_ALLOW_PK = CommunityPvPboardSettings.getProperty("BBSWarehouseAllowPK", false);
		ALLOW_DROP_CALCULATOR = CommunityPvPboardSettings.getProperty("AllowDropCalculator",true);
		DROP_CALCULATOR_DISABLED_TELEPORT = CommunityPvPboardSettings.getProperty("DropCalculatorDisabledTeleport", new int[] {});
		ALLOW_SENDING_IMAGES = CommunityPvPboardSettings.getProperty("AllowSendingImages",true);
	}
	
	public static void loadCommunityPvPclasssettings()
	{
		ExProperties CommunityPvPClassSettings = load(CLASS_MASTER_CONFIG_FILE);

		for (int id : CommunityPvPClassSettings.getProperty("AllowClassMasters", ArrayUtils.EMPTY_INT_ARRAY))
		{
			if (id != 0)
			{
				ALLOW_CLASS_MASTERS_LIST.add(id);
			}
		}

		CLASS_MASTERS_PRICE = CommunityPvPClassSettings.getProperty("ClassMastersPrice", "0,0,0");
		if (CLASS_MASTERS_PRICE.length() >= 5)
		{
			int level = 1;
			for (String id : CLASS_MASTERS_PRICE.split(","))
			{
				CLASS_MASTERS_PRICE_LIST[level] = Integer.parseInt(id);
				level++;
			}
		}
		CLASS_MASTERS_PRICE_ITEM = CommunityPvPClassSettings.getProperty("ClassMastersPriceItem", 57);
	}

	public static void loadCommunityPvPshopsettings()
	{
		ExProperties CommunityPvPshopSettings = load(SHOP_MANAGER_CONFIG_FILE);

		BBS_PVP_ALLOW_BUY = CommunityPvPshopSettings.getProperty("CommunityShopEnable", false);
		BBS_PVP_ALLOW_SELL = CommunityPvPshopSettings.getProperty("CommunitySellEnable", false);
		BBS_PVP_ALLOW_AUGMENT = CommunityPvPshopSettings.getProperty("CommunityAugmentEnable", false);
	}
	
	public static void loadPvPSettings()
	{
		ExProperties pvpSettings = load(PVP_CONFIG_FILE);

		/* KARMA SYSTEM */
		KARMA_MIN_KARMA = pvpSettings.getProperty("MinKarma", 240);
		KARMA_SP_DIVIDER = pvpSettings.getProperty("SPDivider", 7);
		KARMA_LOST_BASE = pvpSettings.getProperty("BaseKarmaLost", 0);
		KARMA_DROP_GM = pvpSettings.getProperty("CanGMDropEquipment", false);
		KARMA_NEEDED_TO_DROP = pvpSettings.getProperty("KarmaNeededToDrop", true);
		DROP_ITEMS_ON_DIE = pvpSettings.getProperty("DropOnDie", false);
		DROP_ITEMS_AUGMENTED = pvpSettings.getProperty("DropAugmented", false);
		KARMA_DROP_ITEM_LIMIT = pvpSettings.getProperty("MaxItemsDroppable", 10);
		MIN_PK_TO_ITEMS_DROP = pvpSettings.getProperty("MinPKToDropItems", 5);
		KARMA_RANDOM_DROP_LOCATION_LIMIT = pvpSettings.getProperty("MaxDropThrowDistance", 70);
		KARMA_DROPCHANCE_BASE = pvpSettings.getProperty("ChanceOfPKDropBase", 20.);
		KARMA_DROPCHANCE_MOD = pvpSettings.getProperty("ChanceOfPKsDropMod", 1.);
		NORMAL_DROPCHANCE_BASE = pvpSettings.getProperty("ChanceOfNormalDropBase", 1.);
		DROPCHANCE_EQUIPPED_WEAPON = pvpSettings.getProperty("ChanceOfDropWeapon", 3);
		DROPCHANCE_EQUIPMENT = pvpSettings.getProperty("ChanceOfDropEquippment", 17);
		DROPCHANCE_ITEM = pvpSettings.getProperty("ChanceOfDropOther", 80);
		KARMA_LIST_NONDROPPABLE_ITEMS = new ArrayList<Integer>();
		for (int id : pvpSettings.getProperty("ListOfNonDroppableItems", new int[]
			{
			57, 1147, 425, 1146, 461, 10, 2368, 7, 6, 2370, 2369, 3500, 3501, 3502, 4422,
			4423, 4424, 2375, 6648, 6649, 6650, 6842, 6834, 6835, 6836, 6837, 6838, 6839,
			6840, 5575, 7694, 6841, 8181
			}))
		{
			KARMA_LIST_NONDROPPABLE_ITEMS.add(id);
		}
		PVP_TIME = pvpSettings.getProperty("PvPTime", 120000);
	}

	public static void loadDragonValleyZoneSettings()
	{
		ExProperties properties = load(ZONE_DRAGONVALLEY_FILE);
		NECROMANCER_MS_CHANCE = properties.getProperty("NecromancerMSChance", 0);
		DWARRIOR_MS_CHANCE = properties.getProperty("DWarriorMSChance", 0.0);
		DHUNTER_MS_CHANCE = properties.getProperty("DHunterMSChance", 0.0);
		BDRAKE_MS_CHANCE = properties.getProperty("BDrakeMSChance", 0);
		EDRAKE_MS_CHANCE = properties.getProperty("EDrakeMSChance", 0);
	}

	public static void loadLairOfAntharasZoneSettings()
	{
		ExProperties properties = load(ZONE_LAIROFANTHARAS_FILE);
		BKARIK_D_M_CHANCE = properties.getProperty("BKarikDMSChance", 0);
	}

	public static void loadAISettings()
	{
		ExProperties aiSettings = load(AI_CONFIG_FILE);

		ALLOW_NPC_AIS = aiSettings.getProperty("AllowNpcAIs", true);
		AI_TASK_MANAGER_COUNT = aiSettings.getProperty("AiTaskManagers", 1);
		AI_TASK_ATTACK_DELAY = aiSettings.getProperty("AiTaskDelay", 1000);
		AI_TASK_ACTIVE_DELAY = aiSettings.getProperty("AiTaskActiveDelay", 1000);
		BLOCK_ACTIVE_TASKS = aiSettings.getProperty("BlockActiveTasks", false);
		ALWAYS_TELEPORT_HOME = aiSettings.getProperty("AlwaysTeleportHome", false);
		RND_WALK = aiSettings.getProperty("RndWalk", true);
		RND_WALK_RATE = aiSettings.getProperty("RndWalkRate", 1);
		RND_ANIMATION_RATE = aiSettings.getProperty("RndAnimationRate", 2);
		AGGRO_CHECK_INTERVAL = aiSettings.getProperty("AggroCheckInterval", 400);
		NONAGGRO_TIME_ONTELEPORT = aiSettings.getProperty("NonAggroTimeOnTeleport", 15000);
		MAX_DRIFT_RANGE = aiSettings.getProperty("MaxDriftRange", 100);
		MAX_PURSUE_RANGE = aiSettings.getProperty("MaxPursueRange", 4000);
		MAX_PURSUE_UNDERGROUND_RANGE = aiSettings.getProperty("MaxPursueUndergoundRange", 2000);
		MAX_PURSUE_RANGE_RAID = aiSettings.getProperty("MaxPursueRangeRaid", 5000);
	}

	public static void loadGeodataSettings()
	{
		ExProperties geodataSettings = load(GEODATA_CONFIG_FILE);

		GEO_X_FIRST = geodataSettings.getProperty("GeoFirstX", 11);
		GEO_Y_FIRST = geodataSettings.getProperty("GeoFirstY", 10);
		GEO_X_LAST = geodataSettings.getProperty("GeoLastX", 26);
		GEO_Y_LAST = geodataSettings.getProperty("GeoLastY", 26);
		GEOFILES_PATTERN = geodataSettings.getProperty("GeoFilesPattern", "(\\d{2}_\\d{2})\\.l2j");
		ALLOW_GEODATA = geodataSettings.getProperty("AllowGeodata", true);
		ALLOW_FALL_FROM_WALLS = geodataSettings.getProperty("AllowFallFromWalls", false);
		ALLOW_KEYBOARD_MOVE = geodataSettings.getProperty("AllowMoveWithKeyboard", true);
		COMPACT_GEO = geodataSettings.getProperty("CompactGeoData", false);
		CLIENT_Z_SHIFT = geodataSettings.getProperty("ClientZShift", 16);
		PATHFIND_BOOST = geodataSettings.getProperty("PathFindBoost", 2);
		PATHFIND_DIAGONAL = geodataSettings.getProperty("PathFindDiagonal", true);
		PATHFIND_MAP_MUL = geodataSettings.getProperty("PathFindMapMul", 2);
		PATH_CLEAN = geodataSettings.getProperty("PathClean", true);
		PATHFIND_MAX_Z_DIFF = geodataSettings.getProperty("PathFindMaxZDiff", 32);
		MAX_Z_DIFF = geodataSettings.getProperty("MaxZDiff", 64);
		MIN_LAYER_HEIGHT = geodataSettings.getProperty("MinLayerHeight", 64);
		REGION_EDGE_MAX_Z_DIFF = geodataSettings.getProperty("RegionEdgeMaxZDiff", 128);
		PATHFIND_MAX_TIME = geodataSettings.getProperty("PathFindMaxTime", 10000000);
		PATHFIND_BUFFERS = geodataSettings.getProperty("PathFindBuffers", "8x96;8x128;8x160;8x192;4x224;4x256;4x288;2x320;2x384;2x352;1x512");
		GEODATA_SKILL_CHECK_TASK_INTERVAL = geodataSettings.getProperty("GeodataSkillCheckTaskInterval", 200);
	}

	public static void loadEventsSettings()
	{
		ExProperties eventSettings = load(EVENTS_CONFIG_FILE);

		EVENT_CofferOfShadowsPriceRate = eventSettings.getProperty("CofferOfShadowsPriceRate", 1.);
		EVENT_CofferOfShadowsRewardRate = eventSettings.getProperty("CofferOfShadowsRewardRate", 1.);
		EVENT_TFH_POLLEN_CHANCE = eventSettings.getProperty("TFH_POLLEN_CHANCE", 5.);
		EVENT_GLITTMEDAL_NORMAL_CHANCE = eventSettings.getProperty("MEDAL_CHANCE", 10.);
		EVENT_GLITTMEDAL_GLIT_CHANCE = eventSettings.getProperty("GLITTMEDAL_CHANCE", 0.1);
		EVENT_L2DAY_LETTER_CHANCE = eventSettings.getProperty("L2DAY_LETTER_CHANCE", 1.);
		EVENT_CHANGE_OF_HEART_CHANCE = eventSettings.getProperty("EVENT_CHANGE_OF_HEART_CHANCE", 5.);
		EVENT_APIL_FOOLS_DROP_CHANCE = eventSettings.getProperty("AprilFollsDropChance", 50.);
		EVENT_BOUNTY_HUNTERS_ENABLED = eventSettings.getProperty("BountyHuntersEnabled", true);
		EVENT_SAVING_SNOWMAN_LOTERY_PRICE = eventSettings.getProperty("SavingSnowmanLoteryPrice", 50000);
		EVENT_SAVING_SNOWMAN_REWARDER_CHANCE = eventSettings.getProperty("SavingSnowmanRewarderChance", 2);
		EVENT_TRICK_OF_TRANS_CHANCE = eventSettings.getProperty("TRICK_OF_TRANS_CHANCE", 10.);
		EVENT_MARCH8_DROP_CHANCE = eventSettings.getProperty("March8DropChance", 10.);
		EVENT_MARCH8_PRICE_RATE = eventSettings.getProperty("March8PriceRate", 1.);
		ENCHANT_CHANCE_MASTER_YOGI_STAFF = eventSettings.getProperty("MasterYogiEnchantChance", 66);
		ENCHANT_MAX_MASTER_YOGI_STAFF = eventSettings.getProperty("MasterYogiEnchantMaxWeapon", 28);
		SAFE_ENCHANT_MASTER_YOGI_STAFF = eventSettings.getProperty("MasterYogiSafeEnchant", 3);
		AllowCustomDropItems = eventSettings.getProperty("AllowCustomDropItems", true);
		CDItemsAllowMinMaxPlayerLvl = eventSettings.getProperty("CDItemsAllowMinMaxPlayerLvl", false);
		CDItemsAllowMinMaxMobLvl = eventSettings.getProperty("CDItemsAllowMinMaxMobLvl", false);
		CDItemsAllowOnlyRbDrops = eventSettings.getProperty("CDItemsAllowOnlyRbDrops", false);
		CDItemsId = eventSettings.getProperty("CDItemsId", new int[] { 57 });
		CDItemsCountDropMin = eventSettings.getProperty("CDItemsCountDropMin", new int[] { 1 });
		CDItemsCountDropMax = eventSettings.getProperty("CDItemsCountDropMax", new int[] { 1 });
		CustomDropItemsChance = eventSettings.getProperty("CustomDropItemsChance", new double[] { 1. });
		CDItemsMinPlayerLvl = eventSettings.getProperty("CDItemsMinPlayerLvl", 20);
		CDItemsMaxPlayerLvl = eventSettings.getProperty("CDItemsMaxPlayerLvl", 85);
		CDItemsMinMobLvl = eventSettings.getProperty("CDItemsMinMobLvl", 20);
		CDItemsMaxMobLvl = eventSettings.getProperty("CDItemsMaxMobLvl", 80);
		EVENT_FLOW_OF_HORROR = eventSettings.getProperty("EnableFlowHorrorEvent", false);
		EVENT_APRIL_FOOLS_DAY = eventSettings.getProperty("EnableAprilFoolsDayEvent", false);
		EVENT_CHRISTMAS = eventSettings.getProperty("EnableChrismasEvent", false);
		EVENT_COFFER_SHADOWS = eventSettings.getProperty("EnableCofferShadowEvent", false);
		EVENT_FREYA = eventSettings.getProperty("EnableFreyaEvent", false);
		EVENT_VITALITY_GIFT = eventSettings.getProperty("EnableVitalityGiftEvent", false);
		EVENT_GLIT_MEDAL = eventSettings.getProperty("EnableGlitteringMedalEvent", false);
		EVENT_HEART = eventSettings.getProperty("EnableHearthEvent", false);
		EVENT_LETTER_COLLECTION = eventSettings.getProperty("EnableLetterCollectionEvent", false);
		EVENT_MARCH8 = eventSettings.getProperty("EnableMarch8Event", false);
		EVENT_MASTER_ENCHANTING = eventSettings.getProperty("EnableMasterEnchantingEvent", false);
		EVENT_PC_CAFFE_EXCHANGE = eventSettings.getProperty("EnablePCCafeExchangeEvent", false);
		EVENT_SAVING_SNOWMAN = eventSettings.getProperty("EnableSavingSnowmanEvent", false);
		EVENT_SUMMER_MELEONS = eventSettings.getProperty("EnableSummerMeleonsEvent", false);
		EVENT_FALL_HARVEST = eventSettings.getProperty("EnableFallHarvestEvent", false);
		EVENT_TRICK_OF_TRANS = eventSettings.getProperty("EnableTrickOfTransEvent", false);
		RANDOM_BOSS_ENABLE = eventSettings.getProperty("EnableRandomBossEvent", false);
		RANDOM_BOSS_ID = eventSettings.getProperty("RandomBossID", 37000);
		RANDOM_BOSS_TIME = eventSettings.getProperty("RandomBossTime", 60);
		RANDOM_BOSS_X = eventSettings.getProperty("RandomBossSpawnX", 20168);
		RANDOM_BOSS_Y = eventSettings.getProperty("RandomBossSpawnY", -15336);
		RANDOM_BOSS_Z = eventSettings.getProperty("RandomBossSpawnZ", -3109);
		ALLOW_FIGHT_CLUB = eventSettings.getProperty("AllowFightClub", true);
		FIGHT_CLUB_HWID_CHECK = eventSettings.getProperty("FightClubHwidCheck", true);
		FIGHT_CLUB_DISALLOW_EVENT = eventSettings.getProperty("FightClubNotAllowedEvent", -1);
		FIGHT_CLUB_EQUALIZE_ROOMS = eventSettings.getProperty("FightClubEqualizeRooms", false);
		EVENT_SANTA_ALLOW = eventSettings.getProperty("AllowSantaEvent", false);
		EVENT_SANTA_CHANCE_MULT = eventSettings.getProperty("SantaItemsChanceMult", 1.0);
	}

	public static void loadOlympiadSettings()
	{
		ExProperties olympSettings = load(OLYMPIAD);

		ENABLE_OLYMPIAD = olympSettings.getProperty("EnableOlympiad", true);
		ALT_OLYMP_PERIOD = olympSettings.getProperty("AltTwoWeeksOlyPeriod", false);
		ENABLE_OLYMPIAD_SPECTATING = olympSettings.getProperty("EnableOlympiadSpectating", true);
		ALT_OLY_START_TIME = olympSettings.getProperty("AltOlyStartTime", 18);
		ALT_OLY_MIN = olympSettings.getProperty("AltOlyMin", 0);
		ALT_OLY_CPERIOD = olympSettings.getProperty("AltOlyCPeriod", 21600000);
		OLYMPIAD_SHOUT_ONCE_PER_START = olympSettings.getProperty("OlyManagerShoutJustOneMessage", false);
		ALT_OLY_WPERIOD = olympSettings.getProperty("AltOlyWPeriod", 604800000);
		ALT_OLY_VPERIOD = olympSettings.getProperty("AltOlyVPeriod", 43200000);
		for (String prop : olympSettings.getProperty("AltOlyDateEndMonthly", "1").split(","))
		{
			ALT_OLY_DATE_END_MONTHLY.add(Integer.parseInt(prop));
		}
		ALT_OLY_DATE_END_WEEKLY = olympSettings.getProperty("AltOlyDateEndWeekly", 0);
		CLASS_GAME_MIN = olympSettings.getProperty("ClassGameMin", 5);
		NONCLASS_GAME_MIN = olympSettings.getProperty("NonClassGameMin", 9);
		TEAM_GAME_MIN = olympSettings.getProperty("TeamGameMin", 4);
		GAME_MAX_LIMIT = olympSettings.getProperty("GameMaxLimit", 70);
		GAME_CLASSES_COUNT_LIMIT = olympSettings.getProperty("GameClassesCountLimit", 30);
		GAME_NOCLASSES_COUNT_LIMIT = olympSettings.getProperty("GameNoClassesCountLimit", 60);
		GAME_TEAM_COUNT_LIMIT = olympSettings.getProperty("GameTeamCountLimit", 10);
		ALT_OLY_REG_DISPLAY = olympSettings.getProperty("AltOlyRegistrationDisplayNumber", 100);
		ALT_OLY_BATTLE_REWARD_ITEM = olympSettings.getProperty("AltOlyBattleRewItem", 13722);
		ALT_OLY_CLASSED_RITEM_C = olympSettings.getProperty("AltOlyClassedRewItemCount", 50);
		ALT_OLY_NONCLASSED_RITEM_C = olympSettings.getProperty("AltOlyNonClassedRewItemCount", 40);
		ALT_OLY_TEAM_RITEM_C = olympSettings.getProperty("AltOlyTeamRewItemCount", 50);
		ALT_OLY_COMP_RITEM = olympSettings.getProperty("AltOlyCompRewItem", 13722);
		ALT_OLY_GP_PER_POINT = olympSettings.getProperty("AltOlyGPPerPoint", 1000);
		ALT_OLY_HERO_POINTS = olympSettings.getProperty("AltOlyHeroPoints", 180);
		ALT_OLY_RANK1_POINTS = olympSettings.getProperty("AltOlyRank1Points", 120);
		ALT_OLY_RANK2_POINTS = olympSettings.getProperty("AltOlyRank2Points", 80);
		ALT_OLY_RANK3_POINTS = olympSettings.getProperty("AltOlyRank3Points", 55);
		ALT_OLY_RANK4_POINTS = olympSettings.getProperty("AltOlyRank4Points", 35);
		ALT_OLY_RANK5_POINTS = olympSettings.getProperty("AltOlyRank5Points", 20);
		OLYMPIAD_STADIAS_COUNT = olympSettings.getProperty("OlympiadStadiasCount", 160);
		OLYMPIAD_BEGIN_TIME = olympSettings.getProperty("OlympiadBeginTime", 120);
		OLYMPIAD_BATTLES_FOR_REWARD = olympSettings.getProperty("OlympiadBattlesForReward", 15);
		OLYMPIAD_POINTS_DEFAULT = olympSettings.getProperty("OlympiadPointsDefault", 50);
		OLYMPIAD_POINTS_WEEKLY = olympSettings.getProperty("OlympiadPointsWeekly", 10);
		OLYMPIAD_OLDSTYLE_STAT = olympSettings.getProperty("OlympiadOldStyleStat", false);
		ALT_OLY_WAIT_TIME = olympSettings.getProperty("AltOlyWaitTime", 120);
		ALT_OLY_PORT_BACK_TIME = olympSettings.getProperty("AltOlyPortBackTime", 20);
		OLYMPIAD_PLAYER_IP = olympSettings.getProperty("OlympiadPlayerIp", false);
		OLYMPIAD_BAD_ENCHANT_ITEMS_ALLOW = olympSettings.getProperty("OlympiadUnEquipBadEnchantItem", false);
		OLY_ENCH_LIMIT_ENABLE = olympSettings.getProperty("OlyEnchantLimit", false);
		OLY_ENCHANT_LIMIT_WEAPON = olympSettings.getProperty("OlyEnchantLimitWeapon", 0);
		OLY_ENCHANT_LIMIT_ARMOR = olympSettings.getProperty("OlyEnchantLimitArmor", 0);
		OLY_ENCHANT_LIMIT_JEWEL = olympSettings.getProperty("OlyEnchantLimitJewel", 0);
		OLY_SHOW_OPPONENT_PERSONALITY = olympSettings.getProperty("OlympiadShowOpponentPersonality", false);
	}

	public static void loadRankingCBConfig()
	{
		ExProperties rankingCBSetting = load(RANKING_CB_CONFIG_FILE);

		SERVER_RANKING_REWARD_ITEM_ID = rankingCBSetting.getProperty("ServerRankingRewardItemId", 57);
		SERVER_RANKING_REWARD_ITEM_COUNT = rankingCBSetting.getProperty("ServerRankingRewardItemCount", new int[] { 10,3,1 });
	}

	public static boolean ALLOW_CB_AUGMENTATION;
	public static int COMMUNITY_AUGMENTATION_MIN_LEVEL;
	public static boolean COMMUNITY_AUGMENTATION_ALLOW_JEWELRY;

	public static void loadAugmentCBConfig()
	{
		ExProperties augmentCBSetting = load(AUGMENT_CB_CONFIG_FILE);

		ALLOW_CB_AUGMENTATION = augmentCBSetting.getProperty("EnableCommunityAugmentation", false);
		COMMUNITY_AUGMENTATION_MIN_LEVEL = augmentCBSetting.getProperty("MinLevelToAugment", 46);
		COMMUNITY_AUGMENTATION_ALLOW_JEWELRY = augmentCBSetting.getProperty("AllowJewelryyAugmentation", false);
	}

	// Community Academy
	public static boolean ENABLE_COMMUNITY_ACADEMY;
	public static String SERVICES_ACADEMY_REWARD;
	public static long ACADEMY_MIN_ADENA_AMOUNT;
	public static long ACADEMY_MAX_ADENA_AMOUNT;
	public static long MAX_TIME_IN_ACADEMY;
	public static int ACADEMY_INVITE_DELAY;

	public static void loadAcademyCBConfig()
	{
		ExProperties academyCBSetting = load(ACADEMY_CB_CONFIG_FILE);

		ENABLE_COMMUNITY_ACADEMY = academyCBSetting.getProperty("EnableAcademyBoard", false);
		SERVICES_ACADEMY_REWARD = academyCBSetting.getProperty("AcademyRewards", "57");
		ACADEMY_MIN_ADENA_AMOUNT = academyCBSetting.getProperty("MinAcademyPrice", 1);
		ACADEMY_MAX_ADENA_AMOUNT = academyCBSetting.getProperty("MaxAcademyPrice", 1000000000);
		MAX_TIME_IN_ACADEMY = academyCBSetting.getProperty("KickAcademyAfter", 259200000);
		ACADEMY_INVITE_DELAY = academyCBSetting.getProperty("InviteDelay", 5);
	}

	public static void loadPremiumConfig()
	{
		ExProperties premiumConf = load(PREMIUM_FILE);

		PREMIUM_ACCOUNT_TYPE = premiumConf.getProperty("RateBonusType", 0);
		PREMIUM_ACCOUNT_PARTY_GIFT_ID = premiumConf.getProperty("PartyGift", 1);
		ENTER_WORLD_SHOW_HTML_PREMIUM_BUY = premiumConf.getProperty("PremiumHTML", false);
		//ENTER_WORLD_SHOW_HTML_LOCK = premiumConf.getProperty("LockHTML", false);
		ENTER_WORLD_SHOW_HTML_PREMIUM_DONE = premiumConf.getProperty("PremiumDone", false);
		ENTER_WORLD_SHOW_HTML_PREMIUM_ACTIVE = premiumConf.getProperty("PremiumInfo", false);
		SERVICES_RATE_TYPE = premiumConf.getProperty("RateBonusType", Bonus.NO_BONUS);
		SERVICES_RATE_CREATE_PA = premiumConf.getProperty("RateBonusCreateChar", 0);
		SERVICES_RATE_BONUS_PRICE = premiumConf.getProperty("RateBonusPrice", new int[] { 1500 });
		SERVICES_RATE_BONUS_ITEM = premiumConf.getProperty("RateBonusItem", new int[] { 4037 });
		SERVICES_RATE_BONUS_VALUE = premiumConf.getProperty("RateBonusValue", new double[] { 2. });
		SERVICES_RATE_BONUS_DAYS = premiumConf.getProperty("RateBonusTime", new int[] { 30 });
		AUTO_LOOT_PA = premiumConf.getProperty("AutoLootPA", false);
		ENCHANT_CHANCE_WEAPON_PA = premiumConf.getProperty("EnchantChancePA", 66);
		ENCHANT_CHANCE_ARMOR_PA = premiumConf.getProperty("EnchantChanceArmorPA", ENCHANT_CHANCE_WEAPON);
		ENCHANT_CHANCE_ACCESSORY_PA = premiumConf.getProperty("EnchantChanceAccessoryPA", ENCHANT_CHANCE_ARMOR);
		ENCHANT_CHANCE_WEAPON_BLESS_PA = premiumConf.getProperty("EnchantChanceBlessPA", 66);
		ENCHANT_CHANCE_ARMOR_BLESS_PA = premiumConf.getProperty("EnchantChanceArmorBlessPA", ENCHANT_CHANCE_WEAPON);
		ENCHANT_CHANCE_ACCESSORY_BLESS_PA = premiumConf.getProperty("EnchantChanceAccessoryBlessPA", ENCHANT_CHANCE_ARMOR);
		ENCHANT_CHANCE_CRYSTAL_WEAPON_PA = premiumConf.getProperty("EnchantChanceCrystalPA", 66);
		ENCHANT_CHANCE_CRYSTAL_ARMOR_PA = premiumConf.getProperty("EnchantChanceCrystalArmorPA", ENCHANT_CHANCE_CRYSTAL_WEAPON);
		ENCHANT_CHANCE_CRYSTAL_ACCESSORY_PA = premiumConf.getProperty("EnchantChanceCrystalAccessory", ENCHANT_CHANCE_CRYSTAL_ARMOR);
		SERVICES_BONUS_XP = premiumConf.getProperty("RateBonusXp", 1.);
		SERVICES_BONUS_SP = premiumConf.getProperty("RateBonusSp", 1.);
		SERVICES_BONUS_ADENA = premiumConf.getProperty("RateBonusAdena", 1.);
		SERVICES_BONUS_ITEMS = premiumConf.getProperty("RateBonusItems", 1.);
		SERVICES_BONUS_SPOIL = premiumConf.getProperty("RateBonusSpoil", 1.);
		USE_ALT_ENCHANT_PA = Boolean.parseBoolean(premiumConf.getProperty("UseAltEnchantPA", "False"));
		for (String prop : premiumConf.getProperty("EnchantWeaponFighterPA", "100,100,100,70,70,70,70,70,70,70,70,70,70,70,70,35,35,35,35,35").split(","))
		{
			ENCHANT_WEAPON_FIGHT_PA.add(Integer.parseInt(prop));
		}
		for (String prop : premiumConf.getProperty("EnchantWeaponFighterCrystalPA", "100,100,100,70,70,70,70,70,70,70,70,70,70,70,70,35,35,35,35,35").split(","))
		{
			ENCHANT_WEAPON_FIGHT_BLESSED_PA.add(Integer.parseInt(prop));
		}
		for (String prop : premiumConf.getProperty("EnchantWeaponFighterBlessedPA", "100,100,100,70,70,70,70,70,70,70,70,70,70,70,70,35,35,35,35,35").split(","))
		{
			ENCHANT_WEAPON_FIGHT_CRYSTAL_PA.add(Integer.parseInt(prop));
		}
		for (String prop : premiumConf.getProperty("EnchantArmorPA", "100,100,100,66,33,25,20,16,14,12,11,10,9,8,8,7,7,6,6,6").split(","))
		{
			ENCHANT_ARMOR_PA.add(Integer.parseInt(prop));
		}
		for (String prop : premiumConf.getProperty("EnchantArmorCrystalPA", "100,100,100,66,33,25,20,16,14,12,11,10,9,8,8,7,7,6,6,6").split(","))
		{
			ENCHANT_ARMOR_CRYSTAL_PA.add(Integer.parseInt(prop));
		}
		for (String prop : premiumConf.getProperty("EnchantArmorBlessedPA", "100,100,100,66,33,25,20,16,14,12,11,10,9,8,8,7,7,6,6,6").split(","))
		{
			ENCHANT_ARMOR_BLESSED_PA.add(Integer.parseInt(prop));
		}
		for (String prop : premiumConf.getProperty("EnchantJewelryPA", "100,100,100,66,33,25,20,16,14,12,11,10,9,8,8,7,7,6,6,6").split(","))
		{
			ENCHANT_ARMOR_JEWELRY_PA.add(Integer.parseInt(prop));
		}
		for (String prop : premiumConf.getProperty("EnchantJewelryCrystalPA", "100,100,100,66,33,25,20,16,14,12,11,10,9,8,8,7,7,6,6,6").split(","))
		{
			ENCHANT_ARMOR_JEWELRY_CRYSTAL_PA.add(Integer.parseInt(prop));
		}
		for (String prop : premiumConf.getProperty("EnchantJewelryBlessedPA", "100,100,100,66,33,25,20,16,14,12,11,10,9,8,8,7,7,6,6,6").split(","))
		{
			ENCHANT_ARMOR_JEWELRY_BLESSED_PA.add(Integer.parseInt(prop));
		}
		ALT_NEW_CHAR_PREMIUM_ID = premiumConf.getProperty("AltNewCharPremiumId", 0);
	}

	public static void loadTalkGuardConfig()
	{
		ExProperties TalkGuardSetting = load(TALKING_GUARD_CONFIG_FILE);

		TalkGuardChance = TalkGuardSetting.getProperty("TalkGuardChance", 4037);
		TalkNormalChance = TalkGuardSetting.getProperty("TalkNormalChance", 4037);
		TalkNormalPeriod = TalkGuardSetting.getProperty("TalkNormalPeriod", 4037);
		TalkAggroPeriod = TalkGuardSetting.getProperty("TalkAggroPeriod", 4037);
	}

	public static void loadBufferConfig()
	{
		ExProperties BufferConfig = load(BUFFER_CONFIG_FILE);

		BUFFER_ON = BufferConfig.getProperty("Buffer", false);
		ITEM_ID = BufferConfig.getProperty("Item_id", 57);
		BUFFER_PET_ENABLED = BufferConfig.getProperty("Buffer_pet", false);
		BUFFER_PRICE = BufferConfig.getProperty("Buffer_price", 20);
		BUFFER_MIN_LVL = BufferConfig.getProperty("Buffer_min_lvl", 1);
		BUFFER_MAX_LVL = BufferConfig.getProperty("Buffer_max_lvl", 99);
		BUFFER_ALLOW_IN_INSTANCE = BufferConfig.getProperty("BufferAllowInInstance", false);

	}

	// Transferring characters between accounts
	public static void loadAcc_moveConfig()
	{
		ExProperties Acc_moveConfig = load(ACC_MOVE_FILE);

		ACC_MOVE_ENABLED = Acc_moveConfig.getProperty("Acc_move_enabled", false);
		ACC_MOVE_ITEM = Acc_moveConfig.getProperty("Acc_move_item", 57);
		ACC_MOVE_PRICE = Acc_moveConfig.getProperty("Acc_move_price", 57);

	}

	// RWHO system (off emulation)
	public static boolean RWHO_LOG;
	public static int RWHO_FORCE_INC;
	public static int RWHO_KEEP_STAT;
	public static int RWHO_MAX_ONLINE;
	public static boolean RWHO_SEND_TRASH;
	public static int RWHO_ONLINE_INCREMENT;
	public static float RWHO_PRIV_STORE_FACTOR;
	public static int RWHO_ARRAY[] = new int[13];
	public static boolean ENABLE_CAT_NEC_FREE_FARM;
	public static boolean BUFF_STORE_ENABLED;
	public static boolean BUFF_STORE_MP_ENABLED;
	public static double BUFF_STORE_MP_CONSUME_MULTIPLIER;
	public static boolean BUFF_STORE_ITEM_CONSUME_ENABLED;
	public static int BUFF_STORE_NAME_COLOR;
	public static int BUFF_STORE_TITLE_COLOR;
	public static int BUFF_STORE_OFFLINE_NAME_COLOR;
	public static List<Integer> BUFF_STORE_ALLOWED_CLASS_LIST;
	public static List<Integer> BUFF_STORE_FORBIDDEN_SKILL_LIST;

	public static void loadBuffStoreConfig()
	{
		ExProperties buffStoreConfig = load(BUFF_STORE_CONFIG_FILE);

		// Buff Store
		BUFF_STORE_ENABLED = buffStoreConfig.getProperty("BuffStoreEnabled", false);
		BUFF_STORE_MP_ENABLED = buffStoreConfig.getProperty("BuffStoreMpEnabled", true);
		BUFF_STORE_MP_CONSUME_MULTIPLIER = buffStoreConfig.getProperty("BuffStoreMpConsumeMultiplier", 1.0f);
		BUFF_STORE_ITEM_CONSUME_ENABLED = buffStoreConfig.getProperty("BuffStoreItemConsumeEnabled", true);
		BUFF_STORE_NAME_COLOR = Integer.decode("0x" + buffStoreConfig.getProperty("BuffStoreNameColor", "808080"));
		BUFF_STORE_TITLE_COLOR = Integer.decode("0x" + buffStoreConfig.getProperty("BuffStoreTitleColor", "808080"));
		BUFF_STORE_OFFLINE_NAME_COLOR = Integer.decode("0x" + buffStoreConfig.getProperty("BuffStoreOfflineNameColor", "808080"));
		final String[] classes = buffStoreConfig.getProperty("BuffStoreAllowedClassList", "").split(",");
		BUFF_STORE_ALLOWED_CLASS_LIST = new ArrayList<>();
		if (classes.length > 0)
		{
			for (String classId : classes)
			{
				BUFF_STORE_ALLOWED_CLASS_LIST.add(Integer.parseInt(classId));
			}
		}
		final String[] skills = buffStoreConfig.getProperty("BuffStoreForbiddenSkillList", "").split(",");
		BUFF_STORE_FORBIDDEN_SKILL_LIST = new ArrayList<>();
		if (skills.length > 0)
		{
			for (String skillId : skills)
			{
				BUFF_STORE_FORBIDDEN_SKILL_LIST.add(Integer.parseInt(skillId));
			}
		}
	}
	public static boolean BBS_FORGE_ENABLED;
	public static int BBS_FORGE_ENCHANT_ITEM;
	public static int BBS_FORGE_FOUNDATION_ITEM;
	public static int[] BBS_FORGE_FOUNDATION_PRICE_ARMOR;
	public static int[] BBS_FORGE_FOUNDATION_PRICE_WEAPON;
	public static int[] BBS_FORGE_FOUNDATION_PRICE_JEWEL;
	public static int[] BBS_FORGE_ENCHANT_MAX;
	public static int[] BBS_FORGE_WEAPON_ENCHANT_LVL;
	public static int[] BBS_FORGE_ARMOR_ENCHANT_LVL;
	public static int[] BBS_FORGE_JEWELS_ENCHANT_LVL;
	public static int[] BBS_FORGE_ENCHANT_PRICE_WEAPON;
	public static int[] BBS_FORGE_ENCHANT_PRICE_ARMOR;
	public static int[] BBS_FORGE_ENCHANT_PRICE_JEWELS;
	public static int[] BBS_FORGE_AUGMENT_ITEMS_LIST;
	public static long[] BBS_FORGE_AUGMENT_COUNT_LIST;
	public static int BBS_FORGE_WEAPON_ATTRIBUTE_MAX;
	public static int BBS_FORGE_ARMOR_ATTRIBUTE_MAX;
	public static int[] BBS_FORGE_ATRIBUTE_LVL_WEAPON;
	public static int[] BBS_FORGE_ATRIBUTE_LVL_ARMOR;
	public static int[] BBS_FORGE_ATRIBUTE_PRICE_ARMOR;
	public static int[] BBS_FORGE_ATRIBUTE_PRICE_WEAPON;
	public static boolean BBS_FORGE_ATRIBUTE_PVP;
	public static String[] BBS_FORGE_GRADE_ATTRIBUTE;

	public static void loadForgeSettings()
	{
		ExProperties forge = load(FORGE_CONFIG_FILE);
		BBS_FORGE_ENABLED = forge.getProperty("Allow", false);
		BBS_FORGE_ENCHANT_ITEM = forge.getProperty("Item", 4356);
		BBS_FORGE_FOUNDATION_ITEM = forge.getProperty("FoundationItem", 37000);
		BBS_FORGE_FOUNDATION_PRICE_ARMOR = forge.getProperty("FoundationPriceArmor", new int[] { 1, 1, 1, 1, 1, 2, 5, 10 });
		BBS_FORGE_FOUNDATION_PRICE_WEAPON = forge.getProperty("FoundationPriceWeapon", new int[] { 1, 1, 1, 1, 1, 2, 5, 10 });
		BBS_FORGE_FOUNDATION_PRICE_JEWEL = forge.getProperty("FoundationPriceJewel", new int[] { 1, 1, 1, 1, 1, 2, 5, 10 });
		BBS_FORGE_ENCHANT_MAX = forge.getProperty("MaxEnchant", new int[] { 25 });
		BBS_FORGE_WEAPON_ENCHANT_LVL = forge.getProperty("WValue", new int[] { 5 });
		BBS_FORGE_ARMOR_ENCHANT_LVL = forge.getProperty("AValue", new int[] { 5 });
		BBS_FORGE_JEWELS_ENCHANT_LVL = forge.getProperty("JValue", new int[] { 5 });
		BBS_FORGE_ENCHANT_PRICE_WEAPON = forge.getProperty("WPrice", new int[] { 5 });
		BBS_FORGE_ENCHANT_PRICE_ARMOR = forge.getProperty("APrice", new int[] { 5 });
		BBS_FORGE_ENCHANT_PRICE_JEWELS = forge.getProperty("JPrice", new int[] { 5 });
		BBS_FORGE_AUGMENT_ITEMS_LIST = forge.getProperty("AugmentItems", new int[] { 4037, 4037, 4037, 4037 });
		BBS_FORGE_AUGMENT_COUNT_LIST = forge.getProperty("AugmentCount", new long[] { 1L, 3L, 6L, 10L });
		BBS_FORGE_ATRIBUTE_LVL_WEAPON = forge.getProperty("AtributeWeaponValue", new int[] { 25 });
		BBS_FORGE_ATRIBUTE_PRICE_WEAPON = forge.getProperty("PriceForAtributeWeapon", new int[] { 25 });
		BBS_FORGE_ATRIBUTE_LVL_ARMOR = forge.getProperty("AtributeArmorValue", new int[] { 25 });
		BBS_FORGE_ATRIBUTE_PRICE_ARMOR = forge.getProperty("PriceForAtributeArmor", new int[] { 25 });
		BBS_FORGE_ATRIBUTE_PVP = forge.getProperty("AtributePvP", true);
		BBS_FORGE_WEAPON_ATTRIBUTE_MAX = forge.getProperty("MaxWAttribute", 25);
		BBS_FORGE_ARMOR_ATTRIBUTE_MAX = forge.getProperty("MaxAAttribute", 25);
		BBS_FORGE_GRADE_ATTRIBUTE = forge.getProperty("AtributeGrade", "NG:NO;D:NO;C:NO;B:NO;A:ON;S:ON;S80:ON;S84:ON").trim().replaceAll(" ", "").split(";");
	}

	public static boolean ANTIFEED_ENABLE;
	public static boolean ANTIFEED_DUALBOX;
	public static boolean ANTIFEED_DISCONNECTED_AS_DUALBOX;
	public static int ANTIFEED_INTERVAL;
	public static int ANTIFEED_MAX_LVL_DIFFERENCE;

	public static void loadAntiFeedSettings()
	{
		ExProperties antifeed = load(ANTI_FEED_CONFIG_FILE);

		ANTIFEED_ENABLE = antifeed.getProperty("AntiFeedEnable", false);
		ANTIFEED_DUALBOX = antifeed.getProperty("AntiFeedDualbox", true);
		ANTIFEED_DISCONNECTED_AS_DUALBOX = antifeed.getProperty("AntiFeedDisconnectedAsDualbox", true);
		ANTIFEED_INTERVAL = antifeed.getProperty("AntiFeedInterval", 120) * 1000;
		ANTIFEED_MAX_LVL_DIFFERENCE = antifeed.getProperty("AntiFeedMaxLvlDifference", 0);
	}

	public static int DONATION_REWARD_ITEM_ID;
	public static int DONATION_REWARD_MULTIPLIER_PER_EURO;
	public static Map<Integer, Integer> DONATION_REWARD_BONUSES = new LinkedHashMap<>();

	public static void loadDonateRewardSettings()
	{
		ExProperties donate = load(DONATE_REWARD_CONFIG_FILE);

		DONATION_REWARD_ITEM_ID = donate.getProperty("DonationRewardItemId", 57);
		DONATION_REWARD_MULTIPLIER_PER_EURO = donate.getProperty("DonationMultiplierPerEuro", 1);
		final String donationBonus = donate.getProperty("DonationBonusRewards", "300,35;200,25;100,20;25,15;10,10;0,1");
		for (String bonus : donationBonus.split(";"))
		{
			final String[] split = bonus.split(",");
			DONATION_REWARD_BONUSES.put(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
		}
	}

	public static boolean ENABLE_ACHIEVEMENTS;
	public static boolean ENABLE_PLAYER_COUNTERS;

	public static void loadAchievementsConfig()
	{
		final ExProperties achieveConfig = load(ACHIEVEMENTS_CONFIG_FILE);
		ENABLE_ACHIEVEMENTS = achieveConfig.getProperty("EnableAchievements", false);
		ENABLE_PLAYER_COUNTERS = achieveConfig.getProperty("EnablePlayerCounters", true);
	}

	public static void loadPhantomsConfig()
	{
		ExProperties settings = load(PHANTOM_FILE);

		PHANTOM_PLAYERS_ENABLED = settings.getProperty("PhantomPlayersEnabled", false);
		PHANTOM_PLAYERS_ACCOUNT = settings.getProperty("PhantomPlayersAccount", "PhantomPlayerAI");
		PHANTOM_MAX_PLAYERS = settings.getProperty("PhantomMaxPlayers", 1);
		PHANTOM_BANNED_CLASSID = settings.getProperty("PhantomBannedClassIds", new int[]{});
		PHANTOM_BANNED_SETID = settings.getProperty("PhantomBannedSetIds", new int[]{});
		PHANTOM_MAX_WEAPON_GRADE = settings.getProperty("PhantomMaxWeaponGrade", 5);
		PHANTOM_MAX_ARMOR_GRADE = settings.getProperty("PhantomMaxArmorGrade", 5);
		PHANTOM_MAX_JEWEL_GRADE = settings.getProperty("PhantomMaxJewelGrade", 5);
		PHANTOM_SPAWN_MAX = settings.getProperty("PhantomSpawnMax", 1);
		PHANTOM_SPAWN_DELAY = settings.getProperty("PhantomSpawnDelay", 60);
		PHANTOM_MAX_LIFETIME = settings.getProperty("PhantomMaxLifetime", 120);
		CHANCE_TO_ENCHANT_WEAP = settings.getProperty("PhantomChanceEnchantWeap", 0);
		MAX_ENCH_PHANTOM_WEAP = settings.getProperty("PhantomMaxEnchantWeap", 4);
		PHANTOM_MAX_DRIFT_RANGE  = settings.getProperty("MaxDriftRangeForNpc", 1000);
		ALLOW_PHANTOM_CUSTOM_TITLES = settings.getProperty("AllowSetupCustomTitles", false);
		PHANTOM_CHANCE_SET_NOBLE_TITLE = settings.getProperty("ChanceToSetTitle", 30);
		DISABLE_PHANTOM_ACTIONS = settings.getProperty("DisablePhantomActions", false);
		PHANTOM_ALLOWED_NPC_TO_WALK = settings.getProperty("PhantomRoamingNpcs", new int[]{});
		PHANTOM_ROAMING_MAX_WH_CHECKS = settings.getProperty("PhantomRoamingMaxWhChecks", 2);
		PHANTOM_ROAMING_MAX_WH_CHECKS_DWARF = settings.getProperty("PhantomRoamingMaxWhChecksDwarf", 8);
		PHANTOM_ROAMING_MAX_SHOP_CHECKS = settings.getProperty("PhantomRoamingMaxShopChecks", 2);
		PHANTOM_ROAMING_MAX_SHOP_CHECKS_DWARF = settings.getProperty("PhantomRoamingMaxShopChecksDwarf", 5);
		PHANTOM_ROAMING_MAX_NPC_CHECKS = settings.getProperty("PhantomRoamingMaxNpcChecks", 6);
		PHANTOM_ROAMING_MIN_WH_DELAY = settings.getProperty("PhantomRoamingMinWhDelay", 60);
		PHANTOM_ROAMING_MAX_WH_DELAY = settings.getProperty("PhantomRoamingMaxWhDelay", 300);
		PHANTOM_ROAMING_MIN_SHOP_DELAY = settings.getProperty("PhantomRoamingMinShopDelay", 30);
		PHANTOM_ROAMING_MAX_SHOP_DELAY = settings.getProperty("PhantomRoamingMaxShopDelay", 120);
		PHANTOM_ROAMING_MIN_NPC_DELAY = settings.getProperty("PhantomRoamingMinNpcDelay", 45);
		PHANTOM_ROAMING_MAX_NPC_DELAY = settings.getProperty("PhantomRoamingMaxNpcDelay", 120);
		PHANTOM_ROAMING_MIN_PRIVATESTORE_DELAY = settings.getProperty("PhantomRoamingMinPrivatestoreDelay", 2);
		PHANTOM_ROAMING_MAX_PRIVATESTORE_DELAY = settings.getProperty("PhantomRoamingMaxPrivatestoreDelay", 7);
		PHANTOM_ROAMING_MIN_FREEROAM_DELAY = settings.getProperty("PhantomRoamingMinFreeroamDelay", 10);
		PHANTOM_ROAMING_MAX_FREEROAM_DELAY = settings.getProperty("PhantomRoamingMaxFreeroamDelay", 60);
		DISABLE_PHANTOM_RESPAWN = settings.getProperty("DisablePhantomRespawn", false);
		DEBUG_PHANTOMS = settings.getProperty("DebugPhantoms", false);
		PHANTOM_CLANS = settings.getProperty("PhantomClans", new int[]{});
	}

	// Captcha system
	public static boolean ENABLE_CAPTCHA;
	public static boolean CAPTCHA_UNEQUIP;
	public static int CAPTCHA_MIN_MONSTERS;
	public static int CAPTCHA_MAX_MONSTERS;
	public static int CAPTCHA_ATTEMPTS;
	public static int CAPTCHA_SAME_LOCATION_DELAY;
	public static int CAPTCHA_SAME_LOCATION_MIN_KILLS;
	public static String CAPTCHA_FAILED_CAPTCHA_PUNISHMENT_TYPE;
	public static int CAPTCHA_FAILED_CAPTCHA_PUNISHMENT_TIME;
	public static int CAPTCHA_TIME_BETWEEN_TESTED_SECONDS;
	public static int CAPTCHA_TIME_BETWEEN_REPORTS_SECONDS;
	public static int CAPTCHA_MIN_LEVEL;

	public static void loadCaptchaConfig()
	{
		ExProperties settings = load(CAPTCHA_CONFIG_FILE);

		ENABLE_CAPTCHA = settings.getProperty("EnableCaptchaSystem", false);
		CAPTCHA_UNEQUIP = settings.getProperty("CaptchaUnequipWeapon", false);
		CAPTCHA_MIN_MONSTERS = settings.getProperty("CaptchaMinMonstertokill", 1000);
		CAPTCHA_MAX_MONSTERS = settings.getProperty("CaptchaMaxMonstertokill", 2000);
		CAPTCHA_ATTEMPTS = settings.getProperty("CaptchaAttempts", 3);
		CAPTCHA_SAME_LOCATION_DELAY = settings.getProperty("CaptchaSameLocationDelay", 60);
		CAPTCHA_SAME_LOCATION_MIN_KILLS = settings.getProperty("CaptchaSameLocationMinKills", 5);
		CAPTCHA_FAILED_CAPTCHA_PUNISHMENT_TYPE = settings.getProperty("CaptchaPunishmentType", "BANCHAR");
		CAPTCHA_FAILED_CAPTCHA_PUNISHMENT_TIME = settings.getProperty("CaptchaPunishmentTime", -1);
		CAPTCHA_TIME_BETWEEN_TESTED_SECONDS = settings.getProperty("CaptchaDelayBetweenTests", 1800);
		CAPTCHA_TIME_BETWEEN_REPORTS_SECONDS = settings.getProperty("CaptchaReportDelay", 7200);
		CAPTCHA_MIN_LEVEL = settings.getProperty("CaptchaMinLevel", 40);
	}
	
	public static boolean DEV_UNDERGROUND_COLISEUM;
	public static int UNDERGROUND_COLISEUM_MEMBER_COUNT;

	public static void loadUndergroundColiseumSettings()
	{
		final ExProperties coliseumSettings = load(UNDERGROUND_COLISEUM_CONFIG_FILE);

		DEV_UNDERGROUND_COLISEUM = coliseumSettings.getProperty("DebugUndergroundColiseum", false);
		UNDERGROUND_COLISEUM_MEMBER_COUNT = coliseumSettings.getProperty("UndergroundColiseumMemberCount", 7);
	}

	public static boolean ENABLE_DAILY_QUESTS;
	public static boolean ENABLE_CLAN_REWARD;
	public static int CLAN_REWARD_MIN_LEVEL_FOR_REWARD;
	public static int CLAN_REWARD_MAX_LEVEL_FOR_REWARD;
	public static int CLAN_REWARD_MIN_ONLINE_FOR_REWARD;
	public static int CLAN_REWARD_LEVEL;
	public static int CLAN_REWARD_REPUTATION;
	public static boolean CLAN_REWARD_SKILLS;
	public static boolean ALLOW_DUALBOX_EPIC;
	public static boolean ENABLE_SEVEN_DAYS_REWARD;
	public static int REWARD_DAY1_ID;
	public static int REWARD_DAY1_COUNT;
	public static int REWARD_DAY2_ID;
	public static int REWARD_DAY2_COUNT;
	public static int REWARD_DAY3_ID;
	public static int REWARD_DAY3_COUNT;
	public static int REWARD_DAY4_ID;
	public static int REWARD_DAY4_COUNT;
	public static int REWARD_DAY5_ID;
	public static int REWARD_DAY5_COUNT;
	public static int REWARD_DAY6_ID;
	public static int REWARD_DAY6_COUNT;
	public static int REWARD_DAY7_ID;
	public static int REWARD_DAY7_COUNT;

	public static void loadSevenDaysRewardSettings()
	{
		final ExProperties settings = load(SEVEN_DAYS_REWARD_CONFIG_FILE);
		
		ENABLE_SEVEN_DAYS_REWARD = settings.getProperty("EnableSevenDaysReward", false);
		REWARD_DAY1_ID = settings.getProperty("RewardDay1ID", 57);
		REWARD_DAY1_COUNT = settings.getProperty("RewardDay1Count", 1);
		REWARD_DAY2_ID = settings.getProperty("RewardDay2ID", 57);
		REWARD_DAY2_COUNT = settings.getProperty("RewardDay2Count", 1);
		REWARD_DAY3_ID = settings.getProperty("RewardDay3ID", 57);
		REWARD_DAY3_COUNT = settings.getProperty("RewardDay3Count", 1);
		REWARD_DAY4_ID = settings.getProperty("RewardDay4ID", 57);
		REWARD_DAY4_COUNT = settings.getProperty("RewardDay4Count", 1);
		REWARD_DAY5_ID = settings.getProperty("RewardDay5ID", 57);
		REWARD_DAY5_COUNT = settings.getProperty("RewardDay5Count", 1);
		REWARD_DAY6_ID = settings.getProperty("RewardDay6ID", 57);
		REWARD_DAY6_COUNT = settings.getProperty("RewardDay6Count", 1);
		REWARD_DAY7_ID = settings.getProperty("RewardDay7ID", 57);
		REWARD_DAY7_COUNT = settings.getProperty("RewardDay7Count", 1);
	}
	
	public static void loadDailyQuestsSettings()
	{
		final ExProperties settings = load(DAILY_QUESTS_CONFIG_FILE);

		ENABLE_DAILY_QUESTS = settings.getProperty("EnableDailyQuests", false);
		ENABLE_CLAN_REWARD = settings.getProperty("EnableClanReward", false);
		CLAN_REWARD_MIN_LEVEL_FOR_REWARD = settings.getProperty("ClanRewardMinLevelForReward", 0);
		CLAN_REWARD_MAX_LEVEL_FOR_REWARD = settings.getProperty("ClanRewardMaxLevelForReward", 7);
		CLAN_REWARD_MIN_ONLINE_FOR_REWARD = settings.getProperty("ClanRewardMinOnlineForReward", 0);
		CLAN_REWARD_LEVEL = settings.getProperty("ClanRewardLevel", 7);
		CLAN_REWARD_REPUTATION = settings.getProperty("ClanRewardReputation", 45000);
		CLAN_REWARD_SKILLS = settings.getProperty("ClanRewardSkills", true);
	}

	public static void load()
	{
		loadServerConfig();
		loadTelnetConfig();
		loadResidenceConfig();
		loadOtherConfig();
		loadSpoilConfig();
		loadFormulasConfig();
		loadAltSettings();
		loadServicesSettings();
		loadPvPSettings();
		loadAISettings();
		loadGeodataSettings();
		loadEventsSettings();
		loadOlympiadSettings();
		loadDevelopSettings();
		loadExtSettings();
		loadTopSettings();
		loadRatesConfig();
		loadFightClubSettings();
		loadItemsUseConfig();
		loadSchemeBuffer();
		loadChatConfig();
		loadDonationStore();
		loadNpcConfig();
		loadBossConfig();
		loadEpicBossConfig();
		loadWeddingConfig();
		loadInstancesConfig();
		loadItemsSettings();
		abuseLoad();
		loadGMAccess();
		loadPremiumConfig();
		loadForgeSettings();
		loadPvPmodConfig();
		if (ADVIPSYSTEM)
		{
			ipsLoad();
		}
		if (ALLOW_ADDONS_CONFIG)
		{
			AddonsConfig.load();
		}
		loadCommunityPvPboardsettings();
		loadCommunityPvPclasssettings();
		loadCommunityPvPshopsettings();
		loadCommandssettings();
		loadBufferConfig();
		loadDonateConfig();
		loadTalkGuardConfig();
		loadAcc_moveConfig();
		loadBuffStoreConfig();
		loadRankingCBConfig();
		loadAntiFeedSettings();
		loadDonateRewardSettings();
		loadAchievementsConfig();
		loadPhantomsConfig();
		loadCaptchaConfig();
		loadAugmentCBConfig();
		loadAcademyCBConfig();
		loadUndergroundColiseumSettings();
		loadDailyQuestsSettings();
		loadSevenDaysRewardSettings();
	}

	private Config()
	{

	}

	public static void abuseLoad()
	{
		List<Pattern> tmp = new ArrayList<Pattern>();

		LineNumberReader lnr = null;
		try
		{
			String line;

			lnr = new LineNumberReader(new InputStreamReader(new FileInputStream(ANUSEWORDS_CONFIG_FILE), "UTF-8"));

			while ((line = lnr.readLine()) != null)
			{
				StringTokenizer st = new StringTokenizer(line, "\n\r");
				if (st.hasMoreTokens())
					tmp.add(Pattern.compile(".*" + st.nextToken() + ".*", Pattern.DOTALL | Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE));
			}

			ABUSEWORD_LIST = tmp.toArray(new Pattern[tmp.size()]);
			tmp.clear();
			_log.info("Abuse: Loaded " + ABUSEWORD_LIST.length + " abuse words.");
		}
		catch (IOException e1)
		{
			_log.warn("Error reading abuse: " + e1);
		}
		finally
		{
			try
			{
				if (lnr != null)
					lnr.close();
			}
			catch (Exception e2)
			{
				// nothing
			}
		}
	}

	public static void loadGMAccess()
	{
		gmlist.clear();
		loadGMAccess(new File(GM_PERSONAL_ACCESS_FILE));
		File dir = new File(GM_ACCESS_FILES_DIR);
		if (!dir.exists() || !dir.isDirectory())
		{
			_log.info("Dir " + dir.getAbsolutePath() + " not exists.");
			return;
		}
		for (File f : dir.listFiles())
		{
			// hidden Ä‚â€�Ă˘â‚¬ĹľÄ‚ďż˝Ă‚Â°Ä‚ďż˝Ă‚Ä…Ä‚ďż˝Ă‚Â»Ä‚â€�Ă˘â‚¬Ä… Ä‚ďż˝ÄŹĹĽËťÄ‚ďż˝Ă˘â‚¬Ë� Ä‚ďż˝Ă‚Â¸Ä‚ďż˝Ă‚Ĺ‚Ä‚ďż˝Ă‚ËťÄ‚ďż˝Ă‚ÄľÄ‚â€�Ă˘â€šÂ¬Ä‚ďż˝Ă‚Â¸Ä‚â€�Ă˘â€šÂ¬Ä‚â€�Ä†â€™Ä‚ďż˝Ă‚ÂµÄ‚ďż˝Ă‚Ä˝
			if (!f.isDirectory() && f.getName().endsWith(".xml"))
			{
				loadGMAccess(f);
			}
		}
	}

	public static void loadGMAccess(File file)
	{
		try
		{
			Field fld;
			// File file = new File(filename);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
			factory.setIgnoringComments(true);
			Document doc = factory.newDocumentBuilder().parse(file);

			for (Node z = doc.getFirstChild(); z != null; z = z.getNextSibling())
			{
				for (Node n = z.getFirstChild(); n != null; n = n.getNextSibling())
				{
					if (!n.getNodeName().equalsIgnoreCase("char"))
					{
						continue;
					}

					PlayerAccess pa = new PlayerAccess();
					for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling())
					{
						Class<?> cls = pa.getClass();
						String node = d.getNodeName();

						if (node.equalsIgnoreCase("#text"))
						{
							continue;
						}

						// Synerge - Support to allow only the commands listed here
						if (node.equalsIgnoreCase("AllowedCommands"))
						{
							final String[] commands = d.getAttributes().getNamedItem("set").getNodeValue().split(";");
							for (String command : commands)
							{
								if (command.trim().isEmpty())
									continue;

								pa.AllowedCommands.add(command.trim());
							}
							continue;
						}

						try
						{
							fld = cls.getField(node);
						}
						catch (NoSuchFieldException e)
						{
							_log.info("Not found desclarate ACCESS name: " + node + " in XML Player access Object");
							continue;
						}

						if (fld.getType().getName().equalsIgnoreCase("boolean"))
						{
							fld.setBoolean(pa, Boolean.parseBoolean(d.getAttributes().getNamedItem("set").getNodeValue()));
						}
						else if (fld.getType().getName().equalsIgnoreCase("int"))
						{
							fld.setInt(pa, Integer.valueOf(d.getAttributes().getNamedItem("set").getNodeValue()));
						}
					}
					gmlist.put(pa.PlayerID, pa);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static String getField(String fieldName)
	{
		Field field = FieldUtils.getField(Config.class, fieldName);

		if (field == null)
		{
			return null;
		}

		try
		{
			return String.valueOf(field.get(null));
		}
		catch (IllegalArgumentException e)
		{

		}
		catch (IllegalAccessException e)
		{

		}

		return null;
	}

	public static boolean setField(String fieldName, String value)
	{
		Field field = FieldUtils.getField(Config.class, fieldName);

		if (field == null)
		{
			return false;
		}

		try
		{
			if (field.getType() == boolean.class)
			{
				field.setBoolean(null, BooleanUtils.toBoolean(value));
			}
			else if (field.getType() == int.class)
			{
				field.setInt(null, NumberUtils.toInt(value));
			}
			else if (field.getType() == long.class)
			{
				field.setLong(null, NumberUtils.toLong(value));
			}
			else if (field.getType() == double.class)
			{
				field.setDouble(null, NumberUtils.toDouble(value));
			}
			else if (field.getType() == String.class)
			{
				field.set(null, value);
			}
			else
			{
				return false;
			}
		}
		catch (IllegalArgumentException e)
		{
			return false;
		}
		catch (IllegalAccessException e)
		{
			return false;
		}

		return true;
	}

	public static ExProperties load(String filename)
	{
		return load(new File(filename));
	}

	public static ExProperties load(File file)
	{
		ExProperties result = new ExProperties();

		try
		{
			result.load(file);
		}
		catch (IOException e)
		{
			_log.error("Error loading config : " + file.getName() + "!", e);
		}

		return result;
	}

	public static boolean containsAbuseWord(String s)
	{
		for (Pattern pattern : ABUSEWORD_LIST)
			if (pattern.matcher(s).matches())
				return true;
		return false;
	}

	private static void ipsLoad()
	{
		ExProperties ipsSettings = load(ADV_IP_FILE);

		for (int i = 0; i < (ipsSettings.size()); i++)
		{
			int channelId = (i + 2);

			String channels = ipsSettings.getProperty("Channel" + channelId, "-1");
			if (channels.equals("-1"))
				continue;

			AdvIP advip = new AdvIP();
			advip.channelId = channelId;
			advip.channelAdress = channels.split(";")[0];
			advip.channelPort = Integer.parseInt(channels.split(";")[1]);
			GAMEIPS.add(advip);

			_log.info("Added Proxy Channel: " + advip.channelId + " - " + advip.channelAdress + ":" + advip.channelPort);
		}
	}

	public static final File findResource(final String path)
	{
		return findNonCustomResource(path);
	}

	public static final File findNonCustomResource(final String path)
	{
		File file = new File(DATAPACK_ROOT, path);
		if (!file.exists())
		{
			file = new File(path);
		}
		return file;
	}
}