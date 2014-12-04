#include <iostream>
using namespace std;

struct ListNode {
    int val;
    ListNode* next;
    ListNode(int x): val(x), next(NULL) {}
};

void removeRequest(ListNode* head, int* requests, int len)
{
    if (!head)
        return;

    int requestId = 0;
    int curId = 1;
    ListNode* prev = head;
    for (; (prev->next) && (requestId < len); curId++)
    {
        if (curId == requests[requestId]) {
            ListNode* deletedNode = prev->next;
            prev->next = deletedNode->next;
            delete deletedNode;
            requestId++;
        } else {
            prev = prev->next;
        }
    }
}

void printList(ListNode* head)
{
    for (ListNode* cur = head; cur; cur = cur->next)
        cout << cur->val << " ";
    cout << endl;
}

int main(int argc, char** argv)
{
    ListNode* head = new ListNode(0);
    ListNode* cur = head;
    for (int i = 1; i <= 5; ++i)
    {
        cur->next = new ListNode(i);
        cur = cur->next;
    }

    int requests[] = {3, 4};
    cout << "Before removal: ";
    printList(head);
    removeRequest(head, requests, sizeof(requests)/sizeof(int));
    cout << "After removal: ";
    printList(head);
    return 0;
}