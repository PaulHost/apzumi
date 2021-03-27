![mycollages](https://apzumi.com/wp-content/themes/apzumi/img/footer-logo.png)

# Coding challenge - Android

Using Kotlin, create an application that meets these criteria:

    ● It consists of two screens: one with an element list and the other with element details after it has been selected from the list.

    ● It downloads the elements from two APIs:

       a. Bitbucket: https://api.bitbucket.org/2.0/repositories?fields=values.name,values.owner,values.description

       b. GitHub: https://api.github.com/repositories

    ● List element consists of: repository name, user’s name and user’s avatar.

    ● Details screen consists of: repository name, repository details, user’s name and user’s avatar.

    ● Elements downloaded from the Bitbucket API should be visually distinguishable from Github ones (in any way).

    ● Element list needs to have an option to turn on/off alphabetical sorting by repository name.

    ● Pagination is not required.

Nice to have:

    ● Using an architectural pattern of your choice (MVP/MVVM/etc).

    ● Reactive approach (RxJava/RxKotlin).

    ● Handling no-network state (by means of your selection, can vary from a simple message to full offline mode).
