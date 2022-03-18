package com.mogree.dubble.config

import mu.KotlinLogging

class Config {

    object Vimeo{
        const val DESCRIPTION = ""
        const val LICENSE = "" //see Vimeo API Documentation
        const val PRIVACY_VIEW = "anybody" //see Vimeo API Documentation
        const val PRIVACY_EMBED = "public" //see Vimeo API Documentation
        const val REVIEW_LINK = false
    }

    object FileStatus {
        const val PROCESSING = "Processing"
        const val FINISHED = "Finished"
    }

    companion object {
        val LOGGER = KotlinLogging.logger {}
    }

    object ItemType {
        const val MODEL_MEDIA = 4
    }

    object StaticUsername {
        const val GUEST = "guest"
        const val UNKNOWN = "unknown"
        const val ANONYMOUS = "anonymousUser"
    }

    object EntityName {
        const val USER = "User"
        const val MEDIA = "Media"
        const val PRODUCT = "Product"
        const val CONTACT = "Contact"
        const val CUSTOMER = "Customer"
    }

    object MediaModule {
        const val ACCOUNT = "account"
        const val PRODUCT = "product"
        const val CONTACT = "contact"
        const val CUSTOMER = "customer"
    }

    object FileExtension {
        const val MOV = ".mov"
        const val QT = ".qt"
        const val MP4 = ".mp4"
        const val GP = ".3gp"
    }

    object Database {
        const val TABLE_USER = "user"
        const val TABLE_MEDIA = "media"
        const val TABLE_CONTACT = "contact"
        const val TABLE_PRODUCT = "product"
        const val TABLE_CUSTOMER = "customer"
        const val TABLE_PRODUCT_REVIEW = "product_review"
        const val TABLE_PRODUCT_PRESS_INFO = "product_press_info"
        const val TABLE_MONITOR = "monitor"
    }

    object Mail {

        object Uri {
            const val ACTIVATION = "/register"
            const val RESET_PASSWORD = "/reset-password"
            const val PRODUCT = "/product"
        }

        object Template {
            const val ACTIVATION = "mail/account/activation"
            const val RESET_PASSWORD = "mail/account/reset-password"
            const val INVITE_USER = "mail/account/invite-user"
            const val PRODUCT_PUBLISHED = "mail/product/product-published"
        }

        object Variable {
            const val ACTIVATION_LINK = "activationLink"
            const val RESET_PASSWORD_LINK = "resetPasswordLink"
            const val PRODUCT_LINK = "productLink"
            const val VIDEOS = "videos"
            const val TEASER_IMAGE = "teaserImage"
            const val CONTACT_IMAGE = "contactImage"
            const val COMPANY_IMAGE = "companyImage"
            const val TAB_COUNT = "tabCount"
            const val PDF_FILES = "pdfFiles"
            const val COMPANY_NAME = "companyName"
        }

        object Subject {
            const val ACTIVATION = "Aktivierung des Accounts"
            const val RESET_PASSWORD = "Passwort vergessen"
            const val INVITE_USER = "Aktivieren Sie Ihren Dubble Account"
            const val PRODUCT_PUBLISHED = "Neuigkeiten von"
        }

    }

    object Sms {
        const val FROM_PUBLISHED_TEXT = "von "
        const val HEADER_PUBLISHED_TEXT = "Hallo und schönen guten Tag "
        const val PRODUCT_PUBLISHED_TEXT = ",\n\n\n" +
                "Ich habe für Sie einige aktuelle und interessante Informationen übersichtlich auf einer Seite zusammengestellt. \n" +
                "Bitte klicken Sie auf den Link - ich freue mich auf Ihre Rückmeldung. \n" +
                "Schöne Grüße \n"
    }

    object RegexPattern {
        const val HEX_COLOR = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$"
    }

    object ResponseMessage {
        const val MISSING_ID = "Id is missing!"
        const val INVALID_BODY = "Invalid body!"
        const val ITEM_DELETED = "Item deleted!"
        const val ITEM_NOT_FOUND = "Item not found!"
        const val UNAUTHORIZED = "Unauthorized!"

        fun missingId(entityName: String): String {
            return "Id of '$entityName' is missing!"
        }

        fun invalidModel(entityName: String): String {
            return "Invalid '$entityName' model!"
        }

        fun invalidAttribute(attributeName: String): String {
            return "Invalid '$attributeName' attribute!"
        }

        fun deleted(entityName: String): String {
            return "$entityName is deleted!"
        }

        fun notFound(entityName: String): String {
            return "$entityName not found!"
        }

    }

    object Template {

        const val PRODUCT_DETAIL = "product/product-page"
        const val NOT_FOUND = "not-found"
        const val WEB_DOMAIN = "webDomain"


    }

    object ErrorMessagesGerman {
        const val DB_CONNECTION = "DB-Verbindung nicht möglich"
        const val UNAUTHORIZED = "Nicht autorisiert"
        const val TOKEN_INVALID = "Token ungültig"
        const val MEDIA_TYPE_IMPLEMENTATION = "Medientyp-Implementierung ist nicht vorhanden."
        const val USER_EXISTENCE = "Benutzer existiert nicht"
        const val INVALID_OLD_PASSWORD = "Altes Passwort ist ungültig"
        const val INVALID_PASSWORD = "Passwort ist ungültig"
        const val INVALID_REQUEST_PARAM = "Ungültiger Parameter für die Anfrage"
        const val USER_NOT_ACTIVATED = "Benutzer ist nicht aktiviert"
        const val CONTACT_USED_IN_PRODUCT = "Kontakt kann nicht gelöscht werden, da er in einem anderen Produkt verwendet wird"
        const val INVALID_ACCESS = "Ungültiger Zugriff"
        const val ITEM_EXISTENCE = "Objekt existiert nicht auf dem Server"
        const val MEDIA_NOTFOUND = "Medienobjekt nicht gefunden"
        const val CUSTOMER_EMAIL = "E-Mail des Kunden muss gesetzt werden.!"
        const val CUSTOMER_PHONE = "Die Telefonnummer des Kunden muss gesetzt werden!"
        const val NOTIFICATION_NOT_POSSIBLE = "Ohne zugeordneten Kunden ist eine Benachrichtigung nicht möglich!"
        const val INVALID_BODY = "Body ist nicht zulässig!"
        const val EXTENSION_NOT_IMPLEMENTED = "Keine Implementierung für diese Erweiterung gefunden"
        const val INVALID_FILE = "Datei ist nicht zulässig"
        const val READ_FILE= "Die Datei konnte nicht gelesen werden"
        const val PARSE_EXTENSION = "Konnte die Dateierweiterung nicht parsen"
        const val PAGING_PARAM_MISSING = "Paging: Offset oder Limit fehlt"
        const val INVALID_ACTIVATION_CODE = "Ungültiger Aktivierungscode"
        const val USER_ALREADY_ACTIVATED = "Der Benutzer ist bereits aktiviert"
        const val FIELDNAME_NOT_NULL_ONE = "Die Contact ID darf nicht null und muss größer als 1 sein"
        const val CUSTOMERMODEL_FIELD_MISSING = "CustomerModel Pflichtfelder fehlen"
        const val PRODUCT_NOT_PUBLISHED = "Das Produkt wurde noch nicht veröffentlicht!"
        const val SECTION_HEADING_NOT_NULL_EMPTY = "Bitte fügen Sie Abschnittsüberschriften (z.B.: Feedback unserer Kunden oder Zusätzliche Informationen) hinzu."
        const val HEADING_NOT_NULL_EMPTY = "Bitte fügen Sie Überschriften (z.B.: Feedback unserer Kunden oder Zusätzliche Informationen) hinzu."
        const val INVITE_USER_EMAIL_EXISTS = "Der Mitarbeiter hat bereits ein eigenes Konto"
        fun CONTACT_EXISTENCE(contactId: String) = "Kontakt mit der ID `$contactId` existiert nicht!"
        fun USER_EMAIL_EXISTS(email: String) = "Ein Benutzer mit der E-Mail '$email' existiert bereits"
        fun GENDER_NOT_SUPPORTED(gender:String) = "Geschlecht '$gender' wird nicht unterstützt"
        fun WRITE_FILE(fileName:String) = "Die Datei $fileName konnte nicht geschrieben werden"
        fun CREATE_FILE(fileName:String) = "Die Datei $fileName konnte nicht erstellt werden"
        fun FIELDNAME_NOT_NULL_EMPTY(fieldName:String) = "$fieldName sollte nicht null oder leer sein!"
        fun FIELDNAME_NOT_NULL_ZERO(fieldName:String) = "$fieldName darf nicht null und muss größer als 0 sein"
        fun INVALID_PATTERN(fieldName:String,value:String) = "Ungültiges $fieldName Schema  - '$value'!"
        fun INVALID_EMAIL_PATTERN(email:String) = "Ungültiges Schema der E-Mail '$email'"
        fun INVALID_ATTRIBUTE(attributeName: String) = "Ungültiges Attribut '$attributeName'"
        fun NO_USER_FOR_EMAIL(email: String) = "Es gibt keinen Benutzer mit der E-Mail $email" //+email
        fun NO_USER_FOR_ID(userId: String) = "Der Benutzer mit der ID `$userId` existiert nicht" //+userID
        fun NO_CUSTOMER_FOR_ID(customerId: String) = "Kunde mit der ID `$customerId` existiert nicht!"
        fun NO_USER_FOR_RESETCODE(passwordResetCode: String) = "Ein Benutzer mit dem 'passwordResetCode = $passwordResetCode' existiert nicht"
    }

    object ErrorMessages {
        const val DB_CONNECTION = "DB Connection not working"
        const val UNAUTHORIZED = "Unauthorized"
        const val TOKEN_INVALID = "Token invalid"
        const val MEDIA_TYPE_IMPLEMENTATION = "media type implementation is missing"
        const val USER_EXISTENCE = "User does not exist"
        const val INVALID_OLD_PASSWORD = "Invalid old password"
        const val INVALID_PASSWORD = "Invalid password"
        const val INVALID_REQUEST_PARAM = "Invalid request param"
        const val USER_NOT_ACTIVATED = "User is not activated"
        const val CONTACT_USED_IN_PRODUCT = "Contact can not be deleted because it used by some product"
        const val INVALID_ACCESS = "invalid access"
        const val ITEM_EXISTENCE = "item does not exist on the server"
        const val MEDIA_NOTFOUND = "media item not found"
        const val CUSTOMER_EMAIL = "Customer's email must be set!"
        const val CUSTOMER_PHONE = "Customer's phone number must be set!"
        const val NOTIFICATION_NOT_POSSIBLE = "Notification is not possible without assigned customer!"
        const val INVALID_BODY = "Body is not valid!"
        const val EXTENSION_NOT_IMPLEMENTED = "no implementation for the extension found"
        const val INVALID_FILE = "file is not valid"
        const val READ_FILE = "Could not read file"
        const val PARSE_EXTENSION = "Could not parse file extension"
        const val PAGING_PARAM_MISSING = "Paging: Offset or Limit is missing"
        const val INVALID_ACTIVATION_CODE = "Invalid activation code"
        const val USER_ALREADY_ACTIVATED = "The user already activated"
        const val FIELDNAME_NOT_NULL_ONE = "Contact ID must be not null and greater then 1!"
        const val CUSTOMERMODEL_FIELD_MISSING = "CustomerModel required fields are missing"
        const val PRODUCT_NOT_PUBLISHED = "The product is not published yet!"
        fun USER_EMAIL_EXISTS(email: String) = "A user with the email '$email' already exists"
        fun CONTACT_EXISTENCE(contactId: String) = "Contact with id `$contactId` does not exist!"
        fun GENDER_NOT_SUPPORTED(gender:String) = "Gender '$gender' is not supported"
        fun WRITE_FILE(fileName:String) = "Could not write to file $fileName"
        fun CREATE_FILE(fileName:String) = "Could not create file $fileName"
        fun FIELDNAME_NOT_NULL_EMPTY(fieldName:String) = "$fieldName must be not null and not empty!"
        fun FIELDNAME_NOT_NULL_ZERO(fieldName:String) = "$fieldName must be not null and must be greater than 0"
        fun INVALID_PATTERN(fieldName:String,value:String) = "Invalid $fieldName pattern - '$value'!"
        fun INVALID_EMAIL_PATTERN(email:String) = "Invalid pattern of email '$email'"
        fun INVALID_ATTRIBUTE(attributeName: String) = "Invalid '$attributeName' attribute!"
        fun NO_USER_FOR_EMAIL(email: String) = "There is no user with the email $email" //+email
        fun NO_USER_FOR_ID(userId: String) = "The user with id `$userId` does not exist" //+userID
        fun NO_CUSTOMER_FOR_ID(customerId: String) = "Customer with id `$customerId` does not exist!"
        fun NO_USER_FOR_RESETCODE(passwordResetCode: String) = "A user with the 'passwordResetCode = $passwordResetCode' does not exist"


    }

    /* ***** Enums ***** */

    enum class Roles {
        ADMIN, USER
    }

    enum class Genders {
        MALE, FEMALE, OTHER
    }

    enum class ProductPublicationStatuses {
        DRAFT, PUBLISHED
    }

    enum class NotificationType {
        EMAIL, SMS
    }

    enum class LogoPosition {
        left, center, right
    }

}
