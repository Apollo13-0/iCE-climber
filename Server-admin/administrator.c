#include "constants.h"



/// @brief struct to define the games handled by the administrator
struct game {
    int clientSocket;
    int game_number;
    int observers[2];
};

 