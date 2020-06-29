package com.swamwithturtles.decrypto.wordlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WordGenerator {

    private final List<String> WORD_LIST = Arrays.asList(("Hollywood\n"+
            "Well\n"+
            "Foot\n"+
            "New\n"+
            "York\n"+
            "Spring\n"+
            "Court\n"+
            "Tube\n"+
            "Point\n"+
            "Tablet\n"+
            "Slip\n"+
            "Date\n"+
            "Drill\n"+
            "Lemon\n"+
            "Bell\n"+
            "Screen\n"+
            "Fair\n"+
            "Torch\n"+
            "State\n"+
            "Match\n"+
            "Iron\n"+
            "Block\n"+
            "France\n"+
            "Australia\n"+
            "Limousine\n"+
            "Stream\n"+
            "Glove\n"+
            "Nurse\n"+
            "Leprechaun\n"+
            "Play\n"+
            "Tooth\n"+
            "Arm\n"+
            "Diamond\n"+
            "Whale\n"+
            "Comic\n"+
            "Green\n"+
            "Pass\n"+
            "Missile\n"+
            "Paste\n"+
            "Drop\n"+
            "Phoenix\n"+
            "Marble\n"+
            "Staff\n"+
            "Figure\n"+
            "Park\n"+
            "Shadow\n"+
            "Fish\n"+
            "Cotton\n"+
            "Egypt\n"+
            "Theater\n"+
            "Scale\n"+
            "Fall\n"+
            "Track\n"+
            "Force\n"+
            "Dinosaur\n"+
            "Bill\n"+
            "Mine\n"+
            "Turkey\n"+
            "March\n"+
            "Contract\n"+
            "Bridge\n"+
            "Robin\n"+
            "Line\n"+
            "Plate\n"+
            "Band\n"+
            "Fire\n"+
            "Bank\n"+
            "Boom\n"+
            "Cat\n"+
            "Shot\n"+
            "Suit\n"+
            "Chocolate\n"+
            "Roulette\n"+
            "Mercury\n"+
            "Moon\n"+
            "Net\n"+
            "Lawyer\n"+
            "Angel\n"+
            "Spider\n"+
            "Germany\n"+
            "Fork\n"+
            "Pitch\n"+
            "King\n"+
            "Crane\n"+
            "Trip\n"+
            "Dog\n"+
            "Conductor\n"+
            "Part\n"+
            "Witch\n"+
            "Ketchup\n"+
            "Press\n"+
            "Spine\n"+
            "Worm\n"+
            "Alps\n"+
            "Bond\n"+
            "Pan\n"+
            "Racket\n"+
            "Cross\n"+
            "Seal\n"+
            "Aztec\n"+
            "Maple\n"+
            "Parachute\n"+
            "Hotel\n"+
            "Berry\n"+
            "Soldier\n"+
            "Ray\n"+
            "Post\n"+
            "Greece\n"+
            "Square\n"+
            "Mass\n"+
            "Bat\n"+
            "Wave\n"+
            "Car\n"+
            "England\n"+
            "Crash\n"+
            "Tail\n"+
            "Card\n"+
            "Horn\n"+
            "Capital\n"+
            "Fence\n"+
            "Deck\n"+
            "Buffalo\n"+
            "Microscope\n"+
            "Jet\n"+
            "Duck\n"+
            "Ring\n"+
            "Train\n"+
            "Field\n"+
            "Gold\n"+
            "Tick\n"+
            "Check\n"+
            "Queen\n"+
            "Strike\n"+
            "Kangaroo\n"+
            "Spike\n"+
            "Engine\n"+
            "Shakespeare\n"+
            "Wind\n"+
            "Kid\n"+
            "Robot\n"+
            "Note\n"+
            "Ground\n"+
            "Draft\n"+
            "Ham\n"+
            "War\n"+
            "Mouse\n"+
            "Center\n"+
            "Chick\n"+
            "China\n"+
            "Bolt\n"+
            "Spot\n"+
            "Piano\n"+
            "Pupil\n"+
            "Plot\n"+
            "Lion\n"+
            "Police\n"+
            "Head\n"+
            "Litter\n"+
            "Concert\n"+
            "Mug\n"+
            "Vacuum\n"+
            "Straw\n"+
            "Switch\n"+
            "Skyscraper\n"+
            "Laser\n"+
            "Scuba\n"+
            "Diver\n"+
            "Africa\n"+
            "Plastic\n"+
            "Dwarf\n"+
            "Lap\n"+
            "Life\n"+
            "Honey\n"+
            "Horseshoe\n"+
            "Unicorn\n"+
            "Spy\n"+
            "Pants\n"+
            "Wall\n"+
            "Paper\n"+
            "Sound\n"+
            "Ice\n"+
            "Tag\n"+
            "Web\n"+
            "Fan\n"+
            "Orange\n"+
            "Temple\n"+
            "Canada\n"+
            "Scorpion\n"+
            "Mail\n"+
            "Europe\n"+
            "Soul\n"+
            "Apple\n"+
            "Pole\n"+
            "Tap\n"+
            "Mouth\n"+
            "Ambulance\n"+
            "Dress\n"+
            "Cream\n"+
            "Rabbit\n"+
            "Agent\n"+
            "Sock\n"+
            "Nut\n"+
            "Boot\n"+
            "Ghost\n"+
            "Oil\n"+
            "Superhero\n"+
            "Code\n"+
            "Kiwi\n"+
            "Hospital\n"+
            "Film\n"+
            "Button\n"+
            "Snowman\n"+
            "Helicopter\n"+
            "Log\n"+
            "Princess\n"+
            "Time\n"+
            "Cook\n"+
            "Revolution\n"+
            "Shoe\n"+
            "Mole\n"+
            "Spell\n"+
            "Grass\n"+
            "Game\n"+
            "Beat\n"+
            "Hole\n"+
            "Horse\n"+
            "Pirate\n"+
            "Link\n"+
            "Dance\n"+
            "Fly\n"+
            "Pit\n"+
            "Server\n"+
            "School\n"+
            "Lock\n"+
            "Brush\n"+
            "Pool\n"+
            "Star\n"+
            "Jam\n"+
            "Organ\n"+
            "Berlin\n"+
            "Face\n"+
            "Luck\n"+
            "Amazon\n"+
            "Cast\n"+
            "Gas\n"+
            "Club\n"+
            "Sink\n"+
            "Water\n"+
            "Chair\n"+
            "Shark\n"+
            "Copper\n"+
            "Jack\n"+
            "Stick\n"+
            "Olive\n"+
            "Grace\n"+
            "Bear\n"+
            "Glass\n"+
            "Row\n"+
            "London\n"+
            "Rock\n"+
            "Van\n"+
            "Vet\n"+
            "Beach\n"+
            "Charge\n"+
            "Port\n"+
            "Disease\n"+
            "Palm\n"+
            "Moscow\n"+
            "Pin\n"+
            "Washington\n"+
            "Pyramid\n"+
            "Opera\n"+
            "Casino\n"+
            "Pilot\n"+
            "String\n"+
            "Night\n"+
            "Chest\n"+
            "Yard\n"+
            "Teacher\n"+
            "Pumpkin\n"+
            "Thief\n"+
            "Bark\n"+
            "Bug\n"+
            "Mint\n"+
            "Cycle\n"+
            "Telescope\n"+
            "Calf\n"+
            "Air\n"+
            "Box\n"+
            "Thumb\n"+
            "Trunk\n"+
            "Snow\n"+
            "Penguin\n"+
            "Root\n"+
            "Bar\n"+
            "File\n"+
            "Hawk\n"+
            "Battery\n"+
            "Compound\n"+
            "Slug\n"+
            "Octopus\n"+
            "Whip\n"+
            "America\n"+
            "Ivory\n"+
            "Pound\n"+
            "Sub\n"+
            "Cliff\n"+
            "Lab\n"+
            "Eagle\n"+
            "Genius\n"+
            "Ship\n"+
            "Dice\n"+
            "Hood\n"+
            "Heart\n"+
            "Novel\n"+
            "Pipe\n"+
            "Crown\n"+
            "Round\n"+
            "India\n"+
            "Needle\n"+
            "Shop\n"+
            "Watch\n"+
            "Lead\n"+
            "Tie\n"+
            "Table\n"+
            "Cell\n"+
            "Cover\n"+
            "Back\n"+
            "Bomb\n"+
            "Ruler\n"+
            "Forest\n"+
            "Bottle\n"+
            "Space\n"+
            "Hook\n"+
            "Doctor\n"+
            "Ball\n"+
            "Bow\n"+
            "Degree\n"+
            "Rome\n"+
            "Plane\n"+
            "Giant\n"+
            "Nail\n"+
            "Dragon\n"+
            "Stadium\n"+
            "Flute\n"+
            "Carrot\n"+
            "Wake\n"+
            "Fighter\n"+
            "Model\n"+
            "Eye\n"+
            "Mexico\n"+
            "Hand\n"+
            "Swing\n"+
            "Key\n"+
            "Alien\n"+
            "Tower\n"+
            "Poison\n"+
            "Cricket\n"+
            "Cold\n"+
            "Knife\n"+
            "Church\n"+
            "Board\n"+
            "Cloak\n"+
            "Ninja\n"+
            "Olympus\n"+
            "Belt\n"+
            "Light\n"+
            "Death\n"+
            "Stock\n"+
            "Millionaire\n"+
            "Day\n"+
            "Knight\n"+
            "Pie\n"+
            "Bed\n"+
            "Circle\n"+
            "Rose\n"+
            "Change\n"+
            "Cap\n"+
            "Triangle\n"+
            "Drum\n"+
            "Bride\n"+
            "Wagon\n"+
            "University\n"+
            "Hit\n"+
            "Ash\n"+
            "Bass\n"+
            "Astronaut\n"+
            "Doll\n"+
            "Nerve\n"+
            "Coach\n"+
            "Beam\n"+
            "Spoon\n"+
            "Country\n"+
            "Nose\n"+
            "Stamp\n"+
            "Camp\n"+
            "Brain\n"+
            "Leaf\n"+
            "Tutu\n"+
            "Coast\n"+
            "Lunch\n"+
            "Thunder\n"+
            "Potato\n"+
            "Desk\n"+
            "Onion\n"+
            "Elephant\n"+
            "Anchor\n"+
            "Cowboy\n"+
            "Flood\n"+
            "Mohawk\n"+
            "Santa\n"+
            "Pitcher\n"+
            "Barbecue\n"+
            "Leather\n"+
            "Skates\n"+
            "Musketeer\n"+
            "Snap\n"+
            "Saddle\n"+
            "Genie\n"+
            "Mark\n"+
            "Shoulder\n"+
            "Halloween\n"+
            "Newton\n"+
            "Balloon\n"+
            "Fiddle\n"+
            "Craft\n"+
            "Cake\n"+
            "Rat\n"+
            "Tank\n"+
            "Blind\n"+
            "Spirit\n"+
            "Cable\n"+
            "Swamp\n"+
            "Hide\n"+
            "Crystal\n"+
            "Gear\n"+
            "Kiss\n"+
            "Pew\n"+
            "Powder\n"+
            "Turtle\n"+
            "Bacon\n"+
            "Sherlock\n"+
            "Squash\n"+
            "Book\n"+
            "Razor\n"+
            "Dressing\n"+
            "Brick\n"+
            "Brazil\n"+
            "Tear\n"+
            "Stable\n"+
            "Pen\n"+
            "Roll\n"+
            "Christmas\n"+
            "Rubber\n"+
            "Bay\n"+
            "Mother\n"+
            "Kick\n"+
            "Fog\n"+
            "Radio\n"+
            "Crab\n"+
            "Cone\n"+
            "Skull\n"+
            "Wheelchair\n"+
            "Egg\n"+
            "Butter\n"+
            "Cherry\n"+
            "Patient\n"+
            "Dryer\n"+
            "Drawing\n"+
            "Boss\n"+
            "Fever\n"+
            "Banana\n"+
            "Polish\n"+
            "Knot\n"+
            "Paint\n"+
            "Storm\n"+
            "Goldilocks\n"+
            "Pillow\n"+
            "Chain\n"+
            "Moses\n"+
            "Saw\n"+
            "Brother\n"+
            "Rail\n"+
            "Rope\n"+
            "Street\n"+
            "Pad\n"+
            "Wish\n"+
            "Axe\n"+
            "Shorts\n"+
            "Popcorn\n"+
            "Castle\n"+
            "Second\n"+
            "Team\n"+
            "Oasis\n"+
            "Mess\n"+
            "Miss\n"+
            "Avalanche\n"+
            "Texas\n"+
            "Sun\n"+
            "Letter\n"+
            "Rust\n"+
            "Wing\n"+
            "Steel\n"+
            "Ear\n"+
            "Scroll\n"+
            "Bunk\n"+
            "Cane\n"+
            "Venus\n"+
            "Ladder\n"+
            "Purse\n"+
            "Sheet\n"+
            "Sugar\n"+
            "Ace\n"+
            "Scratch\n"+
            "Bucket\n"+
            "Caesar\n"+
            "Disk\n"+
            "Beard\n"+
            "Bulb\n"+
            "Bench\n"+
            "Scarecrow\n"+
            "Igloo\n"+
            "Earth\n"+
            "Ram\n"+
            "Sister\n"+
            "Bread\n"+
            "Record\n"+
            "Dash\n"+
            "Greenhouse\n"+
            "Drone\n"+
            "Steam\n"+
            "Biscuit\n"+
            "Rip\n"+
            "Lip\n"+
            "Cheese\n"+
            "Sack\n"+
            "Dust\n"+
            "Hammer\n"+
            "Cloud\n"+
            "Spray\n"+
            "Kilt\n"+
            "Monkey\n"+
            "Frog\n"+
            "Dentist\n"+
            "Rainbow\n"+
            "Whistle\n"+
            "Kitchen\n"+
            "Lemonade\n"+
            "Floor\n"+
            "Valentine\n"+
            "Pepper\n"+
            "Road\n"+
            "Shed\n"+
            "Milk\n"+
            "Wheel\n"+
            "Magazine\n"+
            "Brass\n"+
            "Tea\n"+
            "Helmet\n"+
            "Flag\n"+
            "Troll\n"+
            "Jail\n"+
            "Sticker\n"+
            "Puppet\n"+
            "Chalk\n"+
            "Sweat\n"+
            "Butterfly\n"+
            "Story\n"+
            "Salad\n"+
            "Armor\n"+
            "Smoke\n"+
            "Cave\n"+
            "Quack\n"+
            "Break\n"+
            "Snake\n"+
            "Mill\n"+
            "Driver\n"+
            "Spurs\n"+
            "Pig\n"+
            "Toast\n"+
            "Penny\n"+
            "Ant\n"+
            "Volume\n"+
            "Lace\n"+
            "Meter\n"+
            "Sling\n"+
            "Delta\n"+
            "Step\n"+
            "Comet\n"+
            "Bath\n"+
            "Polo\n"+
            "Gum\n"+
            "Vampire\n"+
            "Ski\n"+
            "Pocket\n"+
            "Battle\n"+
            "Foam\n"+
            "Salt\n"+
            "Mummy\n"+
            "Chip\n"+
            "Goat\n"+
            "Laundry\n"+
            "Bee\n"+
            "Tattoo\n"+
            "Russia\n"+
            "Tin\n"+
            "Map\n"+
            "Silk\n"+
            "Hose\n"+
            "Sloth\n"+
            "Clock\n"+
            "Bean\n"+
            "Lightning\n"+
            "Bowl\n"+
            "Guitar\n"+
            "Ranch\n"+
            "Pearl\n"+
            "Flat\n"+
            "Virus\n"+
            "Age\n"+
            "Coffee\n"+
            "Marathon\n"+
            "Attic\n"+
            "Wedding\n"+
            "Pop\n"+
            "Trick\n"+
            "Cuckoo\n"+
            "Tornado\n"+
            "Memory\n"+
            "Jockey\n"+
            "Minotaur\n"+
            "Big\n"+
            "Bang\n"+
            "Page\n"+
            "Volcano\n"+
            "Rifle\n"+
            "Boil\n"+
            "Hair\n"+
            "Bicycle\n"+
            "Jumper\n"+
            "Smoothie\n"+
            "Sleep\n"+
            "Pentagon\n"+
            "Groom\n"+
            "River\n"+
            "Farm\n"+
            "Judge\n"+
            "Easter\n"+
            "Mud\n"+
            "Parrot\n"+
            "Comb\n"+
            "Salsa\n"+
            "Eden\n"+
            "Army\n"+
            "Paddle\n"+
            "Saloon\n"+
            "Mile\n"+
            "Blizzard\n"+
            "Quarter\n"+
            "Glasses\n"+
            "Sail\n"+
            "Boxer\n"+
            "Rice\n"+
            "Mirror\n"+
            "Ink\n"+
            "Beer\n"+
            "Makeup\n"+
            "Microwave\n"+
            "Sign\n"+
            "Pizza\n"+
            "Wool\n"+
            "Minute\n"+
            "Sword\n"+
            "Soup\n"+
            "Alaska\n"+
            "Baby\n"+
            "Potter\n"+
            "Shower\n"+
            "Blade\n"+
            "Soap\n"+
            "Tunnel\n"+
            "Peach\n"+
            "Dollar\n"+
            "Tip\n"+
            "Love\n"+
            "Taste\n"+
            "Fuel\n"+
            "Wizard\n"+
            "Garden\n"+
            "Shoot\n"+
            "Shell\n"+
            "Dream\n"+
            "Blues\n"+
            "Earthquake\n"+
            "Pea\n"+
            "Parade\n"+
            "Sled\n"+
            "Smell\n"+
            "Computer\n"+
            "Cow\n"+
            "Peanut\n"+
            "Window\n"+
            "Mustard\n"+
            "Sand\n"+
            "Golf\n"+
            "Crow\n"+
            "Iceland\n"+
            "Apron\n"+
            "Violet\n"+
            "Door\n"+
            "Tiger\n"+
            "Joker\n"+
            "House\n"+
            "Collar\n"+
            "Pine\n"+
            "Magician\n"+
            "Frost\n"+
            "Curry\n"+
            "Bubble\n"+
            "Wood").split("\n"));

    private static final Logger logger = LogManager.getLogger(WordGenerator.class);

    public String getRandomWord() {
        Random rand = new Random();
        return WORD_LIST.get(rand.nextInt(WORD_LIST.size()));
    }

    public List<String> getNewWordList() {
        List<String> wordList = new ArrayList<>();
        while(wordList.size() < 4) {
            String candidate = getRandomWord();
            if(!wordList.contains(candidate)) {
                wordList.add(candidate);
            }
        }
        return wordList;
    }

}
