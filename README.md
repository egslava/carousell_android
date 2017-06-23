# carousell_android
It's a test task for Carousell company. Need to create an "Android client for Reddit" that, actually, doesn't connect to Reddit, but uses only in-memory data storage.


[![The video demonstration](app.gif)](https://youtu.be/tdr-_JLFXBA)

Some positive moments:
1. Tests are written
2. Layouts were done in Sketch first, and, then, I implemented them in code, sometimes, with fixed size to optimize `inflate`s and updates of layouts.
3. The data structure in memory - it's just an array list. But it's always sorted. When I add elements - I add them always to the end of the list, because number of upvotes = 0 for the new element. When I `upvote` something - I just make an element float (as a bubble) to its new position.
4. Changes in the list are realtime. Sometimes it's confusing. That's why I made an introduction video. I'm not sure that I need to do `server emulation`, make swipe-to-refresh and so on.
