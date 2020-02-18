### Producer Consumer Design
POC for Producer Consumer design using a MessageBroker defined to with SRIMS router

### Note
This is a simple design for a Producer/Consumer Model using a MessageBroker, which is built on the concept of     _BlockingQueue_

This is not production grade. This was a used as a design idea for a production grade use. The design has been changed in production so you do have to change it too to fit your needs.

Since this is not production grade, code has not been changed here for the issues below:
# Issues
  1. Built using sync blocks and wait/notify. The addElement(E e) and removeElement() can easily be caught in a deadlock, if the threads are not sleeping for a particular time in between each poll cycle, this can cause a deadlock.
  To remove this issue, replace sync blocks with Lock and use Condition to signal/wait on events.
