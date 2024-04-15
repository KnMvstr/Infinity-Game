package HB_CAPE_MAK.hb_cape_makindu.configuration;

import HB_CAPE_MAK.hb_cape_makindu.repository.*;
import HB_CAPE_MAK.hb_cape_makindu.service.*;
import com.github.javafaker.Faker;
import HB_CAPE_MAK.hb_cape_makindu.entity.*;
import HB_CAPE_MAK.hb_cape_makindu.entity.interfaces.NomenclatureInterface;
import HB_CAPE_MAK.hb_cape_makindu.utils.Slugger;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Component
@AllArgsConstructor
public class InitDataLoaderConfig implements CommandLineRunner {

    private UserRepository userRepository;
    private UserServiceImpl userService;

    private BusinessModelRepository businessModelRepository;
    private BusinessModelServiceImpl businessModelService;

    private PlatformRepository platformRepository;
    private PlatformServiceImpl platformService;

    private PublisherRepository publisherRepository;
    private PublisherServiceImpl publisherService;

    private ClassificationRepository classificationRepository;
    private ClassificationServiceImpl classificationService;

    private GenreRepository genreRepository;
    private GenreServiceImpl genreService;

    private GameRepository gameRepository;
    private GameServiceImpl gameService;

    private ReviewRepository reviewRepository;

    private BCryptPasswordEncoder passwordEncoder;

    private Slugger slugger;

    @Override
    public void run(String... args) {
        createUsers();
        createBusinessModels();
        createPlatforms();
        createPublishers();
        createClassifications();
        createGenres();
        userRepository.flush();
        createGames();
        gameRepository.flush();
        createReview();
        reviewRepository.flush();
    }

    private void createUsers() {
        Faker faker = new Faker(Locale.of("fr"));
        List<String> names = new ArrayList<>();
        for (long i = 1L; i <= 200; i++) {
            if (userRepository.findById(i).isEmpty()) {
                User user;
                if (i == 5 || i == 6) {
                    user = new SuperAdmin();  // Création d'un SuperAdmin pour i=5 et i=6
                    ((SuperAdmin) user).setTitle(faker.superhero().name()); // Définition du titre pour SuperAdmin
                } else if (i <= 4) {
                    user = new Moderator();  // Création d'un Moderator pour i=1 à 4
                    ((Moderator) user).setPhoneNumber(faker.phoneNumber().cellPhone());
                } else {
                    user = new Gamer();  // Création d'un Gamer pour tous les autres i
                    LocalDate localDate = new java.sql.Date(faker.date().birthday(12, 60).getTime()).toLocalDate();
                    ((Gamer) user).setBirthAt(localDate);
                }
                user.setId(i);
                String name;
                do {
                    name = slugger.slugify(faker.funnyName().name().replace(" ", ""));
                } while (names.contains(name));
                names.add(name);
                user.setPseudo(name);
                user.setEmail(faker.internet().safeEmailAddress());
                user.setPwd(passwordEncoder.encode("12345"));
                userRepository.save(user);
            }
        }
    }

    private void createBusinessModels() {
        createNomenclatures(
            businessModelRepository,
            BusinessModel.class,
            List.of("Free-to-Play", "Pay-to-Play", "Pay-to-win")
        );
    }

    private void createPlatforms() {
        createNomenclatures(
            platformRepository,
            Platform.class,
            List.of("Switch", "PC", "PS5", "PS4", "PS3", "XBOX Series X", "XBOX One")
        );
    }

    private void createPublishers() {
        createNomenclatures(
            publisherRepository,
            Publisher.class,
            List.of("Blizzard Entertainment", "Valve", "Riot Games", "Mojang", "Rockstar", "CD Projekt Red", "EA", "2k Games", "Ubisoft", "From Software", "Game Freak", "Nintendo", "Capcom")
        );
    }

    private void createGames() {
        Faker faker = new Faker(Locale.of("fr"));
        List<String> games = List.of("World of Warcraft", "Overwatch", "Diablo IV", "StarCraft II", "Warcraft III : Reforged", "DotA 2", "Counter Strike 2", "Portal 2", "La League des Légendes", "Valorant", "Minecraft", "GTA V", "The Witcher III", "Cyberpunk 2077", "Battlefield V", "Anno 1800", "Elden Ring", "Pokémon : Violet", "Pokémon : Ecarlate", "Zelda : Tears of the Kingdom", "Monster Hunter : World");
        List<Long> businessModels = List.of(2L, 2L, 2L, 2L, 2L, 1L, 2L, 2L, 3L, 1L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L);
        List<Long> publishers = List.of(1L, 1L, 1L, 1L, 1L, 2L, 2L, 2L, 3L, 3L, 4L, 5L, 6L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L);
        List<Long> genres = List.of(16L, 1L, 7L, 15L, 15L, 2L, 1L, 6L, 2L, 1L, 14L, 14L, 4L, 6L, 1L, 15L, 4L, 4L, 4L, 4L, 10L);
        List<Long> platforms = List.of(2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 1L, 1L, 1L, 2L);
        List<String> images = List.of("https://cdn.thegamesdb.net/images/thumb/boxart/front/149-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/32185-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/115193-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/151-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/803-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/2474-1.png", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f2/CS2_Cover_Art.jpg/220px-CS2_Cover_Art.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/914-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/928-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/72904-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/50424-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/20952-1.jpg", "https://calimacil.com/cdn/shop/files/Geralt-calimacil-larp-replica-banner-mobile.jpg?v=1695734545&width=1500", "https://cdn.thegamesdb.net/images/thumb/boxart/front/14517-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/55756-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/64422-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/65101-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/104566-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/104565-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/104362-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/60572-1.jpg");
        List<String> backgroundImage = List.of( "https://wallpapers.com/images/hd/wow-classic-digital-illustration-3yo671y8rip9p4lc.jpg", "https://www.wallpaperflare.com/static/414/186/412/d-va-overwatch-overwatch-d-va-wallpaper.jpg", "https://oyster.ignimgs.com/mediawiki/apis.ign.com/diablo-4/e/e0/D4_CLasses.png","https://i.pinimg.com/originals/d5/13/19/d51319be2e6d301ae006b37087c0a65d.jpg","https://c4.wallpaperflare.com/wallpaper/246/163/668/warcraft-iii-reforged-blizzard-entertainment-warcraft-hd-wallpaper-preview.jpg","https://png.pngtree.com/background/20230613/original/pngtree-dota-2-wallpapers-best-of-dota-2-wallpapers-picture-image_3386446.jpg", "https://i.pinimg.com/originals/c5/52/99/c55299b8012d9033d3d5b059b3869cc1.jpg","https://w.wallha.com/ws/10/dwtgCk8N.jpg","https://images.contentstack.io/v3/assets/blt731acb42bb3d1659/bltc99fef72e5811743/6525b28a6b6b83589976bfca/Street-Demons-Briar_HPO3_WEB.jpg","https://tasbihdigital.com/wp-content/uploads/2022/10/Valorant-Wallpaper-HD.jpg","https://storage.needpix.com/rsynced_images/minecraft-2053886_1280.png", "https://www.impdb.org/images/4/47/GTA_V_Dodo.jpg", "https://virtualbackgrounds.site/wp-content/uploads/2020/11/the-witcher-3-wild-hunt-kaer-morhen.jpg","https://assetsio.reedpopcdn.com/cyberpunk_wallpaper_1.jpg?width=1920&height=1920&fit=bounds&quality=80&format=jpg&auto=webp","https://www.playscope.com/wp-content/uploads/2019/01/battlefieldv_coupsdefoudreimages_0005.jpg","https://cdn.neowin.com/news/images/uploaded/2022/10/1666373154_diesel_product_oregano_home_anno1800_gamescom2018_screenshot_south_america_island_1440x2560-2560x1440-7bd2e0fca19053df695c54781c23b9bd935cf442.jpg","https://www.warlegend.net/wp-content/uploads/Elden-Ring-19.jpg","https://www.nintendo-difference.com/wp-content/uploads/2022/11/Scarlet_Violet_Nov_8_Screenshot_15.jpg","https://tcg.pokemon.com/assets/img/home/wallpapers/wallpaper-69.jpg", "https://www.gematsu.com/wp-content/uploads/2021/12/Game-Page-Featured_The-Legend-of-Zelda-Tears-of-the-Kingdom-Inits.jpg", "https://static1.millenium.org/articles/9/34/39/89/@/1135092-zone-secrete-article_image_d-1.jpg");
        List<String> trailer = List.of("https://www.youtube-nocookie.com/embed/6N3kjHebBKA?si=JYGBt2pB-Z9bz_CW&amp;controls=0&amp;start=5\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share", "https://www.youtube-nocookie.com/embed/E4D-G_kX6uQ?si=XcauPz1LIw0pPMxr&amp;controls=0&amp;start=8\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share", "https://www.youtube-nocookie.com/embed/eWtrb_7Dhfs?si=ugTJ-UpYIVBVF1tw&amp;controls=0&amp;start=3\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share","https://www.youtube-nocookie.com/embed/MVbeoSPqRs4?si=clv3keDBHiba3Tr5&amp;controls=0&amp;start=8\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share","https://www.youtube-nocookie.com/embed/72UbFQO5-m0?si=iWYfus6zzkTGQv6h&amp;controls=0\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share","https://www.youtube-nocookie.com/embed/-cSFPIwMEq4?si=N6_I5k4M6WYW-259&amp;controls=0\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share","https://www.youtube-nocookie.com/embed/kDDnvAr6gGI?si=quhU7XnN-qTE8ekk&amp;controls=0\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share", "https://www.youtube-nocookie.com/embed/A88YiZdXugA?si=CgfcdOv2c6r1unIP&amp;controls=0\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share", "https://www.youtube-nocookie.com/embed/mDYqT0_9VR4?si=P4CIjoD_esGiEc8F&amp;controls=0&amp;start=4\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share", "https://www.youtube-nocookie.com/embed/q2eLOupmraQ?si=ffsK8JUZjRYftG73&amp;controls=0&amp;start=5\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share","https://www.youtube-nocookie.com/embed/QkkoHAzjnUs?si=DFRUdFsdQQk-d6pC&amp;controls=0&amp;start=5\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share","https://www.youtube-nocookie.com/embed/DzRk3E4hepw?si=wyoU-RQBOSDEdNfO&amp;controls=0&amp;start=3\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share","src=\"https://www.youtube-nocookie.com/embed/kfX9n_G0N2Y?si=k4EfsyweF28cyQky&amp;controls=0&amp;start=3\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share","https://www.youtube-nocookie.com/embed/LCZLabOywYU?si=yeyU3BhxMe5_Ecb1&amp;controls=0&amp;start=3\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share", "https://www.youtube-nocookie.com/embed/JEYiNIJHUa8?si=2bdjE6nbGeD4b1BL&amp;controls=0&amp;start=3\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share", "https://www.youtube-nocookie.com/embed/E3Huy2cdih0?si=15rpes-fI2oULrf8&amp;controls=0\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share", "https://www.youtube-nocookie.com/embed/tfQj1aQz8d8?si=wWvm9wjzuUEO5Pgf&amp;controls=0&amp;start=5\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share", "https://www.youtube-nocookie.com/embed/tfQj1aQz8d8?si=wWvm9wjzuUEO5Pgf&amp;controls=0&amp;start=5\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share","https://www.youtube-nocookie.com/embed/NThAJP1VL8M?si=lRHbc2tYb0a091WM&amp;controls=0&amp;start=3\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share", "https://www.youtube-nocookie.com/embed/OotQrKEqe94?si=TVDKUiF5tid3cpYU&amp;controls=0&amp;start=2\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share", "https://www.youtube-nocookie.com/embed/OotQrKEqe94?si=TVDKUiF5tid3cpYU&amp;controls=0&amp;start=2\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share");

        for (int i = 0; i < games.size(); i++) {
            Long id = (long) (i + 1);
            if (gameRepository.findById(id).isEmpty()) {
                Game game = new Game();
                game.setId(id);
                game.setName(games.get(i));
                game.setDescription("<h5>" + faker.yoda().quote() + "</h5></br>" + faker.lorem().paragraph(8));
                game.setImage(images.get(i));
                game.setBackgroundImage(backgroundImage.get(i));
                game.setTrailer(trailer.get(i));
                LocalDate localDate = new java.sql.Date(faker.date().birthday(2, 25).getTime()).toLocalDate();
                game.setReleaseDate(localDate);
                Random rand = new Random();
                game.setModerator((Moderator) userService.findById(rand.nextLong(5 - 1) + 1));
                game.setBusinessModel(businessModelService.findById(businessModels.get(i)));
                game.setClassification(classificationService.findById(rand.nextLong(3 - 1) + 1));
                game.setPublisher(publisherService.findById(publishers.get(i)));
                game.setGenre(genreService.findById(genres.get(i)));
                game.addPlatform(platformService.findById(platforms.get(i)));
                gameRepository.save(game);
            }
        }
    }

    private void createReview() {
        Faker faker = new Faker(Locale.of("fr"));
        for (long i = 1; i <= 500; i++) {
            Review review = new Review();
            review.setId(i);
            Random rand = new Random();
            LocalDateTime localDate = new java.sql.Date(faker.date().birthday(0, 2).getTime()).toLocalDate().atTime(0, 0);
            review.setCreatedAt(localDate);
            review.setRating((float) rand.nextLong(20));
            Optional<User> possibleGamer = Optional.ofNullable(userService.findById(rand.nextLong(200 - 8) + 8));
            if (possibleGamer.isPresent() && possibleGamer.get() instanceof Gamer) {
                review.setGamer((Gamer) possibleGamer.get());
            } else {
                System.out.println("User is not a Gamer or not found for ID: " + (rand.nextLong(200 - 8) + 8));
                continue;  // Skip this review if no valid gamer is found
            }
            // Safely assigning Game
            Optional<Game> game = Optional.ofNullable(gameService.findById(rand.nextLong(21 - 1) + 1));
            game.ifPresent(review::setGame);  // Only set the game if present
            
            review.setDescription(faker.chuckNorris().fact() + "</br>" + faker.lorem().paragraph(3));
            if (i%5 != 0) {
                review.setModerator((Moderator) userService.findById(rand.nextLong(5 - 1) + 1));
                review.setModeratedAt(LocalDateTime.now());
            }
            reviewRepository.save(review);
        }
    }

    private void createClassifications() {
        createNomenclatures(
            classificationRepository,
            Classification.class,
            List.of("PEGI 3", "PEGI 7", "PEGI 12", "PEGI 16", "PEGI 18")
        );
    }

    private void createGenres() {
        createNomenclatures(
            genreRepository,
            Genre.class,
            List.of("FPS", "MOBA", "MMO", "RPG", "Voiture", "Aventure", "Hack'n'Slash", "Simulation", "Sport", "Action", "Horreur", "Plateforme", "Cartes", "Monde ouvert", "Stratégie", "MMO RPG")
        );
    }

    private void createNomenclatures(
            JpaRepository repository,
            Class<?> objectClass,
            List<String> items
    ) {
        items.forEach((name) -> {
            try {
                Long id = (long) items.indexOf(name) + 1;
                if (repository.findById(id).isEmpty()) {
                    Object item = objectClass.getDeclaredConstructor().newInstance();
                    if (item instanceof NomenclatureInterface nameEntity) {
                        nameEntity.setId(id);
                        nameEntity.setName(name);
                        repository.save(nameEntity);
                    }
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
