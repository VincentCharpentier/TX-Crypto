/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tx;

import tx.model.Cryptanalyse;
import static tx.model.Vigenere.decode;
import static tx.model.Vigenere.encode;

/**
 *
 * @author vincetn
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String test = "Napoléon Ier, né le 15 août 1769 à Ajaccio en Corse, dans le royaume de France, et mort le 5 mai 1821 sur l'île Sainte-Hélène, dans l'océan Atlantique, est le premier empereur des Français, du 18 mai 1804 au 6 avril 1814 et du 20 mars 1815 au 22 juin 1815. Second enfant de Charles Bonaparte et Letitia Ramolino, Napoléon Bonaparte est d'abord un militaire, général dans les armées de la Première République française, née de la Révolution, commandant en chef de l'armée d'Italie puis de l'armée d'Orient. Il parvient au pouvoir en 1799 par le coup d'État du 18 brumaire et est Premier consul jusqu'au 2 août 1802, puis consul à vie jusqu'au 18 mai 1804, date à laquelle il est proclamé empereur par un sénatus-consulte suivi d'un plébiscite. Enfin il est sacré empereur en la cathédrale Notre-Dame de Paris le 2 décembre 1804 par le pape Pie VII." +
"En tant que général en chef et chef d'état, Napoléon tente de briser les coalitions montées et financées par le Royaume de Grande-Bretagne et qui rassemblent depuis 1792 les monarchies européennes contre la France et son régime né de la Révolution. Il conduit pour cela les armées françaises d'Italie au Nil et d'Autriche à la Prusse et à la Pologne : ses nombreuses et brillantes victoires (Arcole, Rivoli, Pyramides, Marengo, Austerlitz, Iéna, Friedland), dans des campagnes militaires rapides, disloquent les quatre premières coalitions. Les paix successives, qui mettent un terme à chacune de ces coalitions, renforcent la France et donnent à son chef, Napoléon, un degré de puissance jusqu'alors rarement égalé en Europe lors de la paix de Tilsit (1807)." +
"Il réorganise et réforme durablement l'État et la société. Il porte le territoire français à son extension maximale avec 134 départements en 1812, transformant Rome, Hambourg, Barcelone ou Amsterdam en chefs-lieux de départements français. Il est aussi président de la République italienne de 1802 à 1805, puis roi d’Italie de 1805 à 1814, mais également médiateur de la Confédération suisse de 1803 à 1813 et protecteur de la Confédération du Rhin de 1806 à 1813. Ses victoires lui permettent d'annexer à la France de vastes territoires et de gouverner la majeure partie de l’Europe continentale en plaçant les membres de sa famille sur les trônes de plusieurs royaumes : Joseph sur celui de Naples puis d'Espagne, Louis sur celui de Hollande, Jérôme sur celui de Westphalie et son beau-frère Joachim Murat à Naples. Il crée également un duché de Varsovie, sans oser restaurer formellement l'indépendance polonaise, et soumet temporairement à son influence des puissances vaincues telles que le Royaume de Prusse et l'Empire d'Autriche." +
"Objet, dès son vivant, d'une légende dorée comme d'une légende noire, il doit sa très grande notoriété à son habileté militaire, récompensée par de très nombreuses victoires, et à sa trajectoire politique étonnante1, mais aussi à son régime despotique et très centralisé ainsi qu'à son ambition qui se traduit par des guerres d'agression très meurtrières (au Portugal, en Espagne et en Russie) avec des centaines de milliers de morts et blessés, militaires et civils pour l'ensemble de l'Europe. Il tente également de renforcer le régime colonial français d'Ancien Régime en outre-mer, en rétablissant en particulier l'esclavage en 1802 ce qui provoque la guerre de Saint-Domingue (1802-1803) et la perte définitive de cette colonie, tandis que les britanniques s'assurent le contrôle de toutes les autres colonies entre 1803 et 1810. Cet ennemi britannique toujours invaincu s'obstinant à financer des coalitions de plus en plus générales, les Alliés finissent par remporter des succès décisifs en Espagne (bataille de Vitoria) et en Allemagne (bataille de Leipzig) en 1813. L’intransigeance de Napoléon devant ces sanglants revers lui fait perdre le soutien de pans entiers de la nation française2 tandis que ses anciens alliés ou vassaux se retournent contre lui. Amené à abdiquer en 1814 après la prise de Paris, capitale de l'Empire français, et à se retirer à l'île d'Elbe, il tente de reprendre le pouvoir en France lors de l'épisode des Cent-Jours en 1815. Capable de reconquérir son empire sans coup férir, il amène pourtant la France dans une impasse devant sa mise au ban de l'Europe, avec la lourde défaite de Waterloo qui met fin à l'Empire napoléonien et assure la restauration de la dynastie des Bourbons. Sa mort en exil à Sainte-Hélène sous la garde des Anglais, fait l'objet de nombreuses controverses." +
"Une tradition romantique fait de Napoléon l'archétype du grand homme appelé à bouleverser le monde. C'est ainsi que le comte de Las Cases, auteur du Mémorial de Sainte-Hélène tenta de présenter Napoléon au parlement britannique dans une pétition rédigée en 18183. Élie Faure, dans son ouvrage Napoléon, qui a inspiré Abel Gance, le compare à un « prophète des temps modernes ». D'autres auteurs, tel Victor Hugo, font du vaincu de Sainte-Hélène le « Prométhée moderne ». L'ombre de « Napoléon le Grand » plane sur de nombreux ouvrages de Balzac, Stendhal, Musset, mais aussi de Dostoïevski, de Tolstoï et de bien d'autres encore. Par ailleurs, un courant politique français émerge au xixe siècle, le bonapartisme, se revendiquant de l'action et du mode de gouvernement de Napoléon.";
        String cle = "ADTEC";
        String code = encode(test, cle);
        System.out.println("Texte encodé : " + code);

        double t1 = System.currentTimeMillis();
        String guess_key = Cryptanalyse.guessKey(code);
        double t2 = System.currentTimeMillis();
        System.out.println("Temps de cryptanalyse : "+(t2-t1)+"ms");
        System.out.println("result : " + guess_key);
        System.out.println("Texte décodé : " + decode(code, guess_key));
    }


}
