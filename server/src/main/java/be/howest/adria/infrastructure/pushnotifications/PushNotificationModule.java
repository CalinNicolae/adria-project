package be.howest.adria.infrastructure.pushnotifications;

import be.howest.adria.infrastructure.pushnotifications.database.SubscriptionDb;
import be.howest.adria.infrastructure.pushnotifications.database.SubscriptionDbImpl;
import be.howest.adria.infrastructure.pushnotifications.server.PushServer;
import be.howest.adria.infrastructure.pushnotifications.server.VapidKeys;
import be.howest.adria.infrastructure.pushnotifications.server.VapidKeys.KeyPairJson;
import be.howest.adria.infrastructure.shared.utils.Config;
import nl.martijndwars.webpush.PushService;

import java.util.logging.Logger;

public class PushNotificationModule {

    private static final Logger LOGGER = Logger.getLogger(PushNotificationModule.class.getName());

    public static void tryInit(Config config) {
        try {
            init(config);
        } catch (Exception e) {
            LOGGER.warning("Failed to initialize push notifications: " + e.getMessage());
        }
    }

    private static void init(Config config) throws Exception { //NOSONAR: Mr. Blomme provided this code.
        String vapidKeysPath = config.readSetting("pushnotifications.vapidkeys.path");
        KeyPairJson vapidKeys = VapidKeys.load(vapidKeysPath);
        PushService pushService = new PushService()
                .setSubject("mailto:your-email@example.com") // Challenge: this should be a configuration value
                .setPublicKey(vapidKeys.publicKey)
                .setPrivateKey(vapidKeys.privateKey);

        SubscriptionDb subscriptionDb =
                new SubscriptionDbImpl(config.readSetting("pushnotifications.subscriptions.db.path"));

        PushServer.initialize(pushService, subscriptionDb);
    }

    private PushNotificationModule() {}
}
