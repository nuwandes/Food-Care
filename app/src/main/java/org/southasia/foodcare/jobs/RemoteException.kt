package org.southasia.foodcare.jobs

import retrofit2.Response

class RemoteException(val response: Response<*>) : Exception()
