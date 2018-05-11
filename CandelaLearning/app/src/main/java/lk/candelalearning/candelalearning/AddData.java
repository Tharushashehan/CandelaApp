package lk.candelalearning.candelalearning;

import static lk.candelalearning.candelalearning.R.drawable.question_img01;

/**
 * Created by tharu on 4/21/2018.
 */

public class AddData {
    public void dataEnter(DataBaseHelper myDb){
        if(myDb.numberOfRows("Question")<1){

            //ppr 01 question o1
            myDb.insertQuestion( 1, 1, 1, 2, "ශ්‍රී ලංකාවට නිදහස ලැබුනේ කවදාද?");
            myDb.insertAnswer(1, 1, "1948", 0);
            myDb.insertAnswer(2, 1, "1954", 1);
            myDb.insertAnswer(3, 1, "1978", 1);
            myDb.insertAnswer(4, 1, "1815", 1);

            //ppr 01 question o2
            myDb.insertQuestion( 2, 1, 1, 2, "කුරානය කුමන ආගමට අයත් වුවක්ද?");
            myDb.insertAnswer(5, 2, "සිංහල", 1);
            myDb.insertAnswer(6, 2, "මුස්ලිම්", 1);
            myDb.insertAnswer(7, 2, "ඉස්ලාම්", 0);
            myDb.insertAnswer(8, 2, "හින්දු", 1);

            //ppr 01 question o3
            myDb.insertQuestion( 3, 1, 1, 2, "ජනවාරි මාසයට සිංහල ක්‍රමයට කියන නම කුමක්ද ?");
            myDb.insertAnswer(9, 3, "නවම්", 1);
            myDb.insertAnswer(10, 3, "බක්", 1);
            myDb.insertAnswer(11, 3, "දුරුතු", 0);
            myDb.insertAnswer(12, 3, "උදුවප්", 1);

            //ppr 01 question o4
            myDb.insertQuestion( 4, 1, 1, 2, "අතීතයේ ජම්බුද්වීපය යනුවෙන් හැදින්වුයේ ?");
            myDb.insertAnswer(13, 4, "ලංකාව", 1);
            myDb.insertAnswer(14, 4, "මියන්මාරය", 1);
            myDb.insertAnswer(15, 4, "පකිස්ථානය", 1);
            myDb.insertAnswer(16, 4, "ඉන්දියාව", 0);

            //ppr 01 question o5
            myDb.insertQuestion( 5, 1, 1, 2, "17 යනු ?");
            myDb.insertAnswer(17, 5, "ප්‍රථමක සංක්‍යාවකි", 0);
            myDb.insertAnswer(18, 5, "ඉරට්ටේ සංක්‍යාවකි ", 1);
            myDb.insertAnswer(19, 5, "ග්‍රීක සංක්‍යාවකි", 1);
            myDb.insertAnswer(20, 5, "භාග සංක්‍යාවකි", 1);

            //ppr 01 question o6
            myDb.insertQuestion( 6, 1, 1, 2, "1/2 දශමක සංක්‍යා වලින් ?");
            myDb.insertAnswer(21, 6, "0.3", 1);
            myDb.insertAnswer(22, 6, "2.5", 1);
            myDb.insertAnswer(23, 6, "1", 1);
            myDb.insertAnswer(24, 6, "0.5", 0);

            //ppr 01 question 07
            myDb.insertQuestion( 7, 1, 1, 2, "මම පාසල් යමි යන්න ");
            myDb.insertAnswer(25, 7, "උත්තම පුරුෂ වේ", 0);
            myDb.insertAnswer(26, 7, "මද්යම පුරුෂ වේ", 1);
            myDb.insertAnswer(27, 7, "බහුවචන වේ", 1);
            myDb.insertAnswer(28, 7, "අතීත කාල වේ ", 1);

            //ppr 01 question o8
            myDb.insertQuestion( 8, 1, 1, 2, "විල්පතුව යනු");
            myDb.insertAnswer(29, 8, "බදියුදීන්ගේ රජදනිය වේ", 1);
            myDb.insertAnswer(30, 8, "ඉන්දියාවට අයත් ප්‍රාන්තයකි", 1);
            myDb.insertAnswer(31, 8, "අප්‍රිකාවේ වනාන්තරයකි ", 1);
            myDb.insertAnswer(32, 8, "තර්ජනයට ලක්වූ ලංකාවේ වටිනා වනාන්තරයකි", 0);

            //ppr 01 question o9
            myDb.insertQuestion( 9, 1, 1, 2, "අමල් නයන සමග කලේ කුමක්ද ?");
            myDb.insertAnswer(33, 9, "මල් කැඩීම ", 0);
            myDb.insertAnswer(34, 9, "බෝල ගැසීම", 1);
            myDb.insertAnswer(35, 9, "අල්ලන සෙල්ලම්", 1);
            myDb.insertAnswer(36, 9, "පාඩම්", 1);

            //ppr 01 question 10
            myDb.insertQuestion( 10, 1, 1, 2, "ශ්‍රී ලංකාවට අවරුදු තිහක් පවතී යුද්දයෙන් නිදහස ලැබුනේ කවදාද?");
            myDb.insertAnswer(37, 10, "1948", 1);
            myDb.insertAnswer(38, 10, "2015", 1);
            myDb.insertAnswer(39, 10, "2009", 0);
            myDb.insertAnswer(40, 10, "1978", 1);

            //ppr 01 question 11
            myDb.insertQuestion( 11, 1, 1, 2, "ශ්‍රී ලංකාවේ ජාතික පක්ෂියා කව්ද ?");
            myDb.insertAnswer(41, 11, "මොනරා", 1);
            myDb.insertAnswer(42, 11, "කොවුලා", 1);
            myDb.insertAnswer(43, 11, "කොහා", 1);
            myDb.insertAnswer(44, 11, "වලිකුකුළා", 0);

            //ppr 01 question 12
            myDb.insertQuestion( 12, 1, 1, 2, "ශ්‍රී ලංකාව යනු");
            myDb.insertAnswer(45, 12, "මැදපෙරදිග රටකි", 1);
            myDb.insertAnswer(46, 12, "සිංහල බෞද්ධ රටකි", 0);
            myDb.insertAnswer(47, 12, "යුරෝපා මහාද්වීපයේ රටකි", 1);
            myDb.insertAnswer(48, 12, "චීනයේ ප්‍රාන්තයකි", 1);

            //ppr 01 question 13
            myDb.insertQuestion( 13, 1, 1, 2, "ශ්‍රී ලංකාවේ ජාතික ගස කුමක්ද ?");
            myDb.insertAnswer(49, 13, "බෝ ගස", 1);
            myDb.insertAnswer(50, 13, "තේ ගස", 1);
            myDb.insertAnswer(51, 13, "පොල් ගස", 1);
            myDb.insertAnswer(52, 13, "න ගස", 0);

            //ppr 01 question 14
            myDb.insertQuestion( 14, 1, 1, 2, "ශ්‍රී ලංකාවේ ජාතික ක්‍රීඩාව කුමක්ද ?");
            myDb.insertAnswer(53, 14, "ක්‍රිකට්", 1);
            myDb.insertAnswer(54, 14, "පැසි පන්දු", 1);
            myDb.insertAnswer(55, 14, "දැල් පන්දු", 0);
            myDb.insertAnswer(56, 14, "බේස් බෝල්", 1);

            //ppr 01 question 15
            myDb.insertQuestion( 15, 1, 1, 2, "කළා වැව සාදන ලද්දේ ");
            myDb.insertAnswer(57, 15, "චතුර සේනාරත්න විසිනි ", 1);
            myDb.insertAnswer(58, 15, "දුටුගැමුණු රජු විසිනි ", 1);
            myDb.insertAnswer(59, 15, "ඉංග්‍රීසින් විසිනි ", 1);
            myDb.insertAnswer(60, 15, "දතුසේන රජු විසිනි", 0);

            //ppr 01 question 16
            myDb.insertQuestion( 16, 1, 1, 2, "හෝර්ටන් තැන්න යනු ");
            myDb.insertAnswer(61, 16, "සානුවකි", 0);
            myDb.insertAnswer(62, 16, "නගර සබාවකි", 1);
            myDb.insertAnswer(63, 16, "තේ වත්තකි", 1);
            myDb.insertAnswer(64, 16, "යාපනය දිස්ත්‍රිකකයට අයත්වේ ", 1);

            //ppr 01 question 17
            myDb.insertQuestion( 17, 1, 1, 2, "දුරකථනය සොයාගත්තේ ");
            myDb.insertAnswer(65, 17, "ග්‍රහම්බෙල්", 0);
            myDb.insertAnswer(66, 17, "විමල් වීරවංශ", 1);
            myDb.insertAnswer(67, 17, "ගුග්ලි එල්මෝ මාර්කෝනි", 1);
            myDb.insertAnswer(68, 17, "තෝමස් අලවා එඩිසන්", 1);

            //ppr 01 question 18
            myDb.insertQuestion( 18, 1, 1, 2, "ඉංග්‍රීසි හෝඩියේ අකුරු ගණන කීයද ?");
            myDb.insertAnswer(39, 18, "විස්සයි", 1);
            myDb.insertAnswer(70, 18, "විසිහයයි", 0);
            myDb.insertAnswer(71, 18, "විසිඅටයි", 1);
            myDb.insertAnswer(72, 18, "විසිපහයි", 1);

            //ppr 01 question 19
            myDb.insertQuestion( 19, 1, 1, 2, "ලීකෙලි නැටුම");
            myDb.insertAnswer(73, 19, "අවරුදු කෑමකි", 1);
            myDb.insertAnswer(74, 19, "වෙසක් සැරසිල්ලකි", 1);
            myDb.insertAnswer(75, 19, "පෙරහැර ජන නැටුමකි", 0);
            myDb.insertAnswer(76, 19, "ලංකාවේ ජාතික නැටුමයි", 1);

            //ppr 01 question 20
            myDb.insertQuestion( 20, 1, 1, 2, "වොල්ඩමෝඩ් යනු");
            myDb.insertAnswer(77, 20, "ලංකාවේ ජනාධිපති වේ", 1);
            myDb.insertAnswer(78, 20, "හරිපොටෙර් චිත්‍රපටියේ චරිතයකි", 0);
            myDb.insertAnswer(79, 20, "කතානායකට කියන තවත් නමකි", 1);
            myDb.insertAnswer(80, 20, "වොල්ඩමාන්ලන්තයේ වැසියන්ට කියන නමයි", 1);

        }else if(myDb.numberOfRows("User")<1){
            myDb.insertUser(1, "Default User", "+94712587166");

            myDb.insertGrade(1,4);
            myDb.insertGrade(2,5);
            myDb.insertGrade(3,6);
            myDb.insertGrade(4,7);
            myDb.insertGrade(5,8);
            myDb.insertGrade(6,9);
            myDb.insertGrade(7,10);
            myDb.insertGrade(7,11);

            myDb.insertSubject(1,5,"Common Knowledge");
            myDb.insertSubject(2,5,"Sinhala");
            myDb.insertSubject(3,5,"English");
            myDb.insertSubject(4,5,"History");

            myDb.insertYear(1,1,1,2018);
            myDb.insertYear(2,1,1,2017);
            myDb.insertYear(3,1,1,2016);
            myDb.insertYear(4,1,1,2015);
        }else if (myDb.numberOfRows("Question_Img")<1){
            //Adding images to the question
            myDb.insertQuestion_Img(1,R.drawable.question_img01,"question_img01",1,1);
            myDb.insertQuestion_Img(2,R.drawable.question_img02,"question_img02",1,2);
            myDb.insertQuestion_Img(3,R.drawable.question_img03,"question_img03",1,3);
            myDb.insertQuestion_Img(4,R.drawable.question_img04,"question_img04",1,4);
            myDb.insertQuestion_Img(5,R.drawable.question_img05,"question_img05",1,5);
            myDb.insertQuestion_Img(6,R.drawable.question_img06,"question_img06",1,6);
            myDb.insertQuestion_Img(7,R.drawable.question_img07,"question_img07",1,7);

            //adding images to the answers
            myDb.insertAnswer_Img(1,R.drawable.question_img01,"question_img01",1,1,1);
            myDb.insertAnswer_Img(2,R.drawable.question_img02,"question_img02",1,2,1);
            myDb.insertAnswer_Img(3,R.drawable.question_img03,"question_img03",1,3,1);
            myDb.insertAnswer_Img(4,R.drawable.question_img04,"question_img04",1,4,1);
            myDb.insertAnswer_Img(5,R.drawable.question_img05,"question_img05",1,5,3);
            myDb.insertAnswer_Img(6,R.drawable.question_img06,"question_img06",1,6,3);
            myDb.insertAnswer_Img(7,R.drawable.question_img07,"question_img07",1,7,3);
        }
        //END
    }
}
