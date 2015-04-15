# MIDlet #

  * Maintains a CanvasMap object, which is it's only link to each canvas. this allows for easy disposal of canvases that have been finished.

# Events #

  1. MIDlet defines an EventListener object for each canvas.
  1. A canvas that extends CastawayGameCanvas requires the EventListener to be passed to it's constructor
  1. When an event should occur, the canvas calls the doEvent() method with a new Event object, the event type is specified by selecting on the the final static strings from the Event class
  1. The EventListener  asynchronously registers the event and the Event's referrer and takes the appropriate action in a new thread.
  1. If the event would cause the end of the canvas, the canvas must dispose of its objects, then throw a further EVENT\_CONTROL\_END event. This doesn't interfere with program flow, as flow continues on a different thread. the canvas is disposed of in the background.

# Canvas' #

  * Contain their own game loop thats runs on a new thread
  * Are responsible for disposing of their own objects when instructed to
  * Handle all internal events themselves, pass external events to EventListener