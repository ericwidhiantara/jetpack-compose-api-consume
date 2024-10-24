package com.example.composeapiconsume.android.core.usecase

import com.example.composeapiconsume.android.core.error.Failure
import com.example.composeapiconsume.android.utils.helper.Either

// Assuming `Failure` is already defined in your codebase.
abstract class UseCase<Type, Params> {
    abstract suspend fun call(params: Params): Either<Failure, Type>
}

// Class to handle when UseCase doesn't need parameters
class NoParams
