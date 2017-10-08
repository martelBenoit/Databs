package view;

import java.awt.*;

import javax.swing.*;

/**
 * This class corresponds to the manual of the application
 * @author Alexandre BOUDET and Benoît MARTEL
 * @version 1.0
 */
public class FrameManuel extends JDialog {
	/**
	 * This assignment corresponds to the JPanel of the class
	 */
	private JPanel jp;
	
	/**
	 * This assignment corresponds to the JLabel of the JPanel
	 */
	private JLabel jl;
	
	/**
	 * Initialize a FrameManuel object
	 * @param frame the frame of this application
	 */
	public FrameManuel(FrameApp frame) {

		super(frame);
		this.setTitle("Manuel d'utilisation");
		this.setResizable(false);
		this.setModal(true);
		this.setPreferredSize(new Dimension(800,750));

		jp = new JPanel(new BorderLayout());
		jl = new JLabel("<html><h1 style=\"text-align: center;\">Manuel d'utilisation</h1><p>&nbsp;</p><p>&nbsp;</p><p>Pour commencer &agrave; utiliser notre application, il faut se connecter &agrave; une base de donn&eacute;es.</p><p>&nbsp;</p><p>Pour cela il faut cliquer sur la croix blanche sur fond vert, un popup va alors appara&icirc;tre et ce dernier va vous demandez de renseigner le nom de la connexion ainsi que votre identifiant et mot de passe d'Oracle.</p><p>&nbsp;</p><p>Une fois connect&eacute;, vous allez retrouver toutes les tables d&eacute;j&agrave; impl&eacute;ment&eacute;es d'Oracle dans l'onglet <em><span style=\"text-decoration: underline;\">\"Base de donn&eacute;es\"</span></em> sous forme d'arbre.</p><p>&nbsp;</p><p>Vous pouvez alors cliquer sur l'une de ces derni&egrave;res pour en afficher le contenu. Sur cette table vous avez la possibilit&eacute; d'y rajouter une colonne ou une ligne en cliquant sur les boutons &agrave; droite de l'&eacute;cran. Une fois que vous cliquez sur l'un de ces boutons le reste est tr&egrave;s intuitif.</p><p>&nbsp;</p><p>Si l'envie vous vient de cr&eacute;er ou supprimer une table pas de souci, vous pouvez le faire gr&acirc;ce aux boutons <em><span style=\"text-decoration: underline;\">\"Cr&eacute;er une table\"</span></em> et <span style=\"text-decoration: underline;\"><em>\"Supprimer une table\"</em></span>. Nous avons aussi ajouter une petite fonctionnalit&eacute; bien sympathique qui g&eacute;n&egrave;re automatiquement le code SQL de la cr&eacute;ation de table.</p><p>&nbsp;</p><p>Si vous &ecirc;tes un adepte de l'&eacute;criture du code SQL, vous pouvez faire toutes les manipulations pr&eacute;c&eacute;demment cit&eacute;es dans un script. Il vous suffit pour cela de cr&eacute;er un nouveau fichier, une nouvelle fen&ecirc;tre appara&icirc;tra et vous pourrez alors &eacute;crire votre code, de plus avec notre fonctionnalit&eacute; d'&eacute;diteur de texte la coloration syntaxique ainsi que l'affichage des lignes est pr&eacute;sente.</p><p>&nbsp;</p><p>Si vous n'avez pas le temps de finir votre code pour X raison, ne vous inqui&eacute;tez pas, vous avez juste &agrave; sauvegarder votre fichier en \".sql\" pour ensuite le retrouver.</p><p>&nbsp;</p><p>Vous &ecirc;tes fier de votre travail et souhaitez l'imprimer pour en garder un souvenir cela est aussi possible avec la fonction d'impression que vous retrouver dans le menu et dans la ToolBar.</p><p>&nbsp;</p><p>Une barre rechercher est a votre disposition pour retrouver un mot dans un code contenant un nombre de lignes assez cons&eacute;quent.</p><p>&nbsp;</p><p>En bas &agrave; droite de la fen&ecirc;tre vous pourrez retrouver la fonction <em><span style=\"text-decoration: underline;\">\"Lancer le script\"</span></em> qui va tout simplement ex&eacute;cuter le code SQL pr&eacute;alablement &eacute;crit dans l'&eacute;diteur de texte, son r&eacute;sultat sera alors affich&eacute; dans l'onglet \"R&eacute;sultat\". Si erreur il y a, le message d'erreur sera aussi affich&eacute; dans l'onglet message pr&eacute;vu &agrave; cet effet.</p><p>&nbsp;</p><p>Nous avons aussi ajout&eacute; un petit jeu SQL pour tester vos connaissances en codage.</p><p>&nbsp;</p><p>&nbsp;</p><p>Pr&eacute;sentation &eacute;crite par Alexandre BOUDET et Beno&icirc;t MARTEL</p></html>");
		jp.add(jl, BorderLayout.CENTER);
		this.add(jp);

		this.pack();
		this.setVisible(true);
	}
}
