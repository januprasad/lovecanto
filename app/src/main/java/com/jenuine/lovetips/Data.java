package com.jenuine.lovetips;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jenu on 30/6/15.
 */
public class Data {

    private ArrayList<Post> posts = new ArrayList<Post>();
    public static final Data data = new Data();

    public Data() {

        /*******************/
        List<SubPostData> postList = new ArrayList<SubPostData>();
        postList.add(new SubPostData("First sign is staring", "The first sign of whether a girls likes you is staring. If a girl have an interest on you, she will surely make or steals a star."));
        postList.add(new SubPostData("You will be a great personality to her", "She will always give great values for your concepts and attitude. Gives a special interest to know your plans and ideas. She always loves to discuss on your aspects and your future plans."));
        postList.add(new SubPostData("Notice her response when you in front",
                "If she become nervous or express a bright face you can take it has a positive thing. If she is with her friends then there will be a sudden reaction between them. This gives that their will be a discussion on you and her among them."));
        postList.add(new SubPostData("You will be a great personality to her",
                "She will always give great values for your concepts and attitude. Gives a special interest to know your plans and ideas. She always loves to discuss on your aspects and your future plans."));
        postList.add(new SubPostData("Ignorance makes her jealous",
                "When you ignore her by talking to some other girls you can notice a jealous on her and also she makes a bit mad on you. If you mention her that you are attracted to some other girl, then he will refuse and stop talking to you. This is the best sign for how to know a girl likes you."));
        postList.add(new SubPostData("She always reminds about her status",
                "She will always make clear about her status that she is single. She will try to mention you that she is ready to date with a guy or she need a partner. She will always ask you to join with her."));
        postList.add(new SubPostData("Submit complains to you",
                "She will share all the troubles and problems she faces to you. Give complains to you about her close related one. Especially about parents who always scold her."));
        postList.add(new SubPostData("She try to know your past love",
                "She always know about whether you are engaged or details about your exes. She try maximum to disclose from you."));
        postList.add(new SubPostData("Try to make a touch on you",
                "She makes excuses to touch you and feel your sensation. She will try to hold your hands, in situations which is made by her."));
        posts.add(new Post("How to know a girl likes you", postList));
        /********************************************************/
        postList = new ArrayList<SubPostData>();
        postList.add(new SubPostData("", "You will be able to buy a lots and lots of gifts, but still there will be a perfect gift for your girlfriend.  A gift can represent your feelings. It’s the interpretation of how much you know her. Everyone wants his gift remembered by his girlfriend for a life time. Whenever she receives gift she should remember the special gift given by you. To make this all happen you have to keep some things in mind before you buy a gift. Try to know answers for these questions, which would help you to know her.\n" +
                "\n" +
                "What kind of music she loves?\n" +
                "What is her favorite color?\n" +
                "What kind of dress she wears?\n" +
                "What she does in her free times?\n" +
                "What kind of food she eats?"));

        postList.add(new SubPostData("Selecting a gift",
                "To select a gift there are different perspectives. You can give her a needful gift or a beautiful gift. Most of the boys need to buy a gift which is needful as well as beautiful. Most of such items comes in electronic gift items such a i Pods, Mobile, Laptops. If you are going for high end versions you can go for scooters and cars. Most of this kind of gifts has no selection problem. On getting into small gifts you will be confused more. For this you have to keep in mind the answers of above questions. To have a difference the most appropriate thing is something handmade by you or with the help of others. Such things will remain unique. Glass paintings or writings with your personal happenings or the things happening between you two will be a good one to cherish. If you come to know your girl more close you will be having lots of suggestions by yourself. You can approach her friends or parents to know her tastes. One thing equally important is how you give her the gift. Try to make it a surprise. Or give it in a romantic way. Gifting is the art of making a girl think of you. So just go ahead and make her think soon."));
        posts.add(new Post("How to give a gift to girlfriend", postList));
        /*******************/
        postList = new ArrayList<SubPostData>();


        posts.add(new Post("Benefits of kissing – Reasons for kissing your lover"));
        /*******************/
        posts.add(new Post("How to say “I love you” in creative ways"));
        /*******************/
        posts.add(new Post("How to make Out with a girl & Make it hot"));
        /*********************/

    }

    public static Data getData() {
        return data;
    }

    public ArrayList<Post> getArrayList() {
        return posts;
    }
}
